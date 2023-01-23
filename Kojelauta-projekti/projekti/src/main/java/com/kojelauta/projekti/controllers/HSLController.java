/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.kojelauta.projekti.controllers;

/**
 *
 * @author Reijo
 */

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kojelauta.projekti.entities.Hsl;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

@RestController
@RequestMapping("hsl")
public class HSLController {

    @GetMapping("/{userAddress}")
    List<Hsl> getHsl(@PathVariable String userAddress) throws ClientProtocolException, IOException, JSONException {
        List<Hsl> hsl = new ArrayList<Hsl>();
        String address = userAddress;
        String query = "{\n" +
                "  stops(name: \"" + address + "\") {\n" +
                "    name\n" +
                "    stoptimesWithoutPatterns (numberOfDepartures: 3) {\n" +
                "      realtimeDeparture\n" +
                "      trip {\n" +
                "          route {\n" +
                "            shortName\n" +
                "        }\n" +
                "      }\n" +
                "      headsign\n" +
                "    }\n" +
                "  }\n" +
                "}";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql");
        post.setHeader("Content-Type", "application/graphql");
        post.setEntity(new StringEntity(query, "UTF-8"));

        CloseableHttpResponse response = httpClient.execute(post);

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            System.err.println("Error occurred, status code: " + statusCode);
            return null;
        }

        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());

        JSONObject parsedJson = new JSONObject(responseString);

        // Access the `data` key
        JSONObject data = parsedJson.getJSONObject("data");

        // Access the `stops` key
        JSONArray stops = data.getJSONArray("stops");

        for (int i = 0; i < stops.length(); i++) {
            JSONObject stop = stops.getJSONObject(i);
            String stopName = stop.getString("name");
            JSONArray stoptimesWithoutPatterns = stop.getJSONArray("stoptimesWithoutPatterns");
            // Iterate over the stoptimesWithoutPatterns
            for (int j = 0; j < stoptimesWithoutPatterns.length(); j++) {
                JSONObject stoptime = stoptimesWithoutPatterns.getJSONObject(j);
                String headsign = stoptime.getString("headsign");
                JSONObject trip = stoptime.getJSONObject("trip");
                JSONObject route = trip.getJSONObject("route");
                String shortName = route.getString("shortName");
                int realtimeDeparture = stoptime.getInt("realtimeDeparture");
                LocalTime time = LocalTime.ofSecondOfDay(realtimeDeparture);
                String headsignShorter = headsign;
                if (headsign.length() > 8) {
                    headsignShorter = headsign.substring(0, 8);
                }
                String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm"));
                Hsl hslObject = new Hsl(stopName, formattedTime, headsignShorter, shortName);
                hsl.add(hslObject);
            }
        }
        if (hsl.size() > 6) {
            List<Hsl> limitListTo6 = hsl.subList(0, 6);
            return limitListTo6;
        }
        return hsl;
    }
}

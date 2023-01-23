
package com.kojelauta.projekti.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kristian
 */

@RestController
public class SpotController {

    @GetMapping("/spot-price-today")
    public ResponseEntity<Map<String, Double>> getSpotPrice() throws IOException, JSONException {
        URL url = new URL("https://api.spot-hinta.fi/today");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        JSONArray jsonArray = new JSONArray(content.toString());
        double totalPriceWithTax = 0;
        double highestPriceWithTax = Double.MIN_VALUE;
        double lowestPriceWithTax = Double.MAX_VALUE;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            double priceWithTax = json.getDouble("PriceWithTax");
            totalPriceWithTax += priceWithTax;
            highestPriceWithTax = Math.max(highestPriceWithTax, priceWithTax);
            lowestPriceWithTax = Math.min(lowestPriceWithTax, priceWithTax);
        }
        double averagePriceWithTax = totalPriceWithTax / jsonArray.length();

        Map<String, Double> result = new HashMap<>();

        averagePriceWithTax = averagePriceWithTax * 100;
        averagePriceWithTax = (double) Math.round(averagePriceWithTax * 100) / 100;
        highestPriceWithTax = highestPriceWithTax * 100;
        highestPriceWithTax = (double) Math.round(highestPriceWithTax * 100) / 100;
        lowestPriceWithTax = lowestPriceWithTax * 100;
        lowestPriceWithTax = (double) Math.round(lowestPriceWithTax * 100) / 100;
        result.put("AverageSpot", averagePriceWithTax);
        result.put("HighestSpot", highestPriceWithTax);
        result.put("LowestSpot", lowestPriceWithTax);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/spot-price-tomorrow")
    public ResponseEntity<Map<String, Double>> getSpotPriceTomorrow() throws IOException, JSONException {
        URL url = new URL("https://api.spot-hinta.fi/dayforward");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        JSONArray jsonArray = new JSONArray(content.toString());
        double totalPriceWithTax = 0;
        double highestPriceWithTax = Double.MIN_VALUE;
        double lowestPriceWithTax = Double.MAX_VALUE;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            double priceWithTax = json.getDouble("PriceWithTax");
            totalPriceWithTax += priceWithTax;
            highestPriceWithTax = Math.max(highestPriceWithTax, priceWithTax);
            lowestPriceWithTax = Math.min(lowestPriceWithTax, priceWithTax);
        }
        double averagePriceWithTax = totalPriceWithTax / jsonArray.length();

        Map<String, Double> result = new HashMap<>();
        averagePriceWithTax = averagePriceWithTax * 100;
        averagePriceWithTax = (double) Math.round(averagePriceWithTax * 100) / 100;
        highestPriceWithTax = highestPriceWithTax * 100;
        highestPriceWithTax = (double) Math.round(highestPriceWithTax * 100) / 100;
        lowestPriceWithTax = lowestPriceWithTax * 100;
        lowestPriceWithTax = (double) Math.round(lowestPriceWithTax * 100) / 100;
        result.put("AverageSpot", averagePriceWithTax);
        result.put("HighestSpot", highestPriceWithTax);
        result.put("LowestSpot", lowestPriceWithTax);
        return ResponseEntity.ok(result);
    }
}

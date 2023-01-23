/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kojelauta.projekti.controllers;

import com.google.gson.*;
import com.kojelauta.projekti.entities.Weather;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Taito
 */
@RestController
@RequestMapping("weather")
public class WeatherController {

    static String apiKey = "xxxxx";
    private String city = "Helsinki";

    @GetMapping("/{userCity}")
    private Weather getWeather(@PathVariable String userCity) {
        city = userCity;
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric";
        // Parse JSON from API url into a JSON string.
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String json = response.getBody();
        // Fetch specific field "temp" inside "main" from JSON data.
        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
        JsonObject main = jsonObj.get("main").getAsJsonObject();
        JsonArray jsonArr = jsonObj.get("weather").getAsJsonArray();
        JsonObject w = jsonArr.get(0).getAsJsonObject();
        double temp = main.get("temp").getAsDouble();
        String icon = w.get("icon").getAsString();

        Weather weather = new Weather(temp, icon);

        return weather;
    }
}

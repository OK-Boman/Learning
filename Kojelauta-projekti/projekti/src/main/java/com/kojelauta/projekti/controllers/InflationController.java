
package com.kojelauta.projekti.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Reijo
 */

@RestController
public class InflationController {
    
    @GetMapping(value = "/inflation")
    
    public String getInflation() throws IOException {
        URL url = new URL("https://api.api-ninjas.com/v1/inflation?country=finland");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("X-Api-Key", "WH6WAh34f4LjkUqV3S68jw==DWTVZQ85nKza6sD9");
        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);
        JsonNode firstNode = root.get(0);
        String country = firstNode.get("country").asText();
        double monthlyInflation = firstNode.get("monthly_rate_pct").asDouble();
        double yearlyInflation = firstNode.get("yearly_rate_pct").asDouble();
        return country + "<br> Monthly: " + monthlyInflation +" %" + "<br>" + "Yearly: " + yearlyInflation +" %";
    }
}

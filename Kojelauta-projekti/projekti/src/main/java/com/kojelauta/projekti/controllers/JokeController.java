package com.kojelauta.projekti.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Taito
 */
@RestController
public class JokeController {

    static String apiKey = "xxxxxx";
    String url = "https://api.api-ninjas.com/v1/jokes?limit=1";

    @GetMapping(value = "/joke")
    private String getJoke() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return response.getBody();
    }
}

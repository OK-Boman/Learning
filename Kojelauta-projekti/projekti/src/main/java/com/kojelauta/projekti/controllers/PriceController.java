
package com.kojelauta.projekti.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Kristian
 */

@RestController
public class PriceController {

    static String apiKey = "xxxxxxx";

    @GetMapping(value = "/euribor")
    // return json
    public String getEuribor() {
        String url = "https://api.api-ninjas.com/v1/interestrate?name=Euribor";
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String json = response.getBody();
        return json;

    }

    @GetMapping(value = "/stock-price/{symbol}")
    private String getStock(@PathVariable String symbol) throws Exception {
        String userSymbol = symbol;
        String url = "https://finnhub.io/api/v1/quote?symbol=" + userSymbol
                + "&token=xxxxxx";
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.GET, null, String.class);
        String json = response.getBody();
        return json;

    }

}

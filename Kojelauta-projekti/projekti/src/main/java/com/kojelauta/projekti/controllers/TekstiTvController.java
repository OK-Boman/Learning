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
 *
 * @author Kristian
 */
@RestController
public class TekstiTvController {

    static String apiKey = "xxxxx";

    @GetMapping(value = "/tekstitv/{userSivu}/{userAlasivu}")
    private String getTekstiTv(@PathVariable int userSivu, @PathVariable int userAlasivu) {
        // get the image from url
        String url = "https://external.api.yle.fi/v1/teletext/images/"+userSivu+"/"+userAlasivu+".png?app_id=xxxxx&app_key="+apiKey;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "image/png");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
        byte[] image = response.getBody();
        String base64Image = java.util.Base64.getEncoder().encodeToString(image);
        return base64Image;
    } 
}

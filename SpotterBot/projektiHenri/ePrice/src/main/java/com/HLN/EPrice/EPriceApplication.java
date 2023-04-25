package com.HLN.EPrice;

import com.HLN.EPrice.controllers.Reader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EPriceApplication {
     
    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(EPriceApplication.class, args);
        
         try {
                Reader.readFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
    
  

 


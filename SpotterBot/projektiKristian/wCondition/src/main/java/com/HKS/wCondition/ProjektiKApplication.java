package com.HKS.wCondition;


import com.HKS.wCondition.controllers.Reader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
public class ProjektiKApplication {

	public static void main(String[] args) {
            
            
        // tulostaa konsoliin listan    
//            try {
//                Reader.readFile();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        //käynnistää itse 
        SpringApplication.run(ProjektiKApplication.class, args);
                
	}
        
        @Bean
        public RestTemplate restTemplate (RestTemplateBuilder builder){
            return builder.build();
        }

}

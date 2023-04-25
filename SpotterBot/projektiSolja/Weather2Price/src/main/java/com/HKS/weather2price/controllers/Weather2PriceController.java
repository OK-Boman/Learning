/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HKS.weather2price.controllers;

import com.HKS.Weather2Price.repositories.SearchRepository;
import com.HKS.weather2price.entities.RequestInfo;
import com.HKS.weather2price.entities.Search;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Solja
 */
@RestController
@RequestMapping("searches")
public class Weather2PriceController {

    @Autowired
    SearchRepository repo;
    
    @GetMapping
    List<Search> getAll() {
        return repo.findAll();
    }

    @PostMapping
    Search create(@RequestBody Search s) {
        repo.saveAndFlush(s);
        if (s.getUsername().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name can not be empty");
        }
        return s;
    }
    
    @PutMapping("/{id}")
    Search save(@PathVariable int id, @RequestBody Search s) {
        repo.saveAndFlush(s);
        return s;
    }   
    
//    @GetMapping("/{id}")
//    Search get(@PathVariable int id) {
//        Search s = repo.findById(id).orElse(null);
//        if (s==null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foo not found");
//        }
//        return s;
//    }
    
//    @GetMapping("/wcondition")
//    public WCondition[] getWeather(RestTemplate rt) {
//        WCondition[] wc = rt.getForObject("http://localhost:8081/wCondition", WCondition[].class);
//        return wc;
//    }    
//    
//    @GetMapping("/eprice")
//    public EPrice[] getPrice(RestTemplate rt) {
//        EPrice[] ep = rt.getForObject("http://localhost:8083/ePrice", EPrice[].class);
//        return ep;
//    }      
    
    /*
 
//    */
//    @DeleteMapping("/{id}")
//    RequestInfo delete(@PathVariable int id) {
//        Search s = repo.findById(id).orElse(null);
//        if (a == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foo not found");
//        }
//        repo.deleteById(id);
//        return new RequestInfo("Deleted");
//    }
    
}

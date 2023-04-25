/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HLN.EPrice.controllers;

import com.HLN.EPrice.entities.EPrice;
import com.HLN.EPrice.entities.RequestInfo;
import com.HLN.EPrice.repositories.EPriceRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Henri
 */

@RestController
@RequestMapping("/eprice")
public class EPriceController {

    @Autowired
    EPriceRepository repo;
     
    public EPriceController(EPriceRepository r){
        repo = r;
    }
    
    @GetMapping
    List<EPrice> getAll(@RequestParam(defaultValue = "") String filter) {
        return repo.findFiltered("%" + filter + "%");
    }
    
    @GetMapping("/{id}")
    EPrice getById(@PathVariable int id) {
        EPrice e = repo.findById(id).orElse(null);
        if (e == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Price not found.");
        }
        return e;
    }
    
    @GetMapping("/prices")
    public EPrice[] getEPrice(RestTemplate rt) {
        EPrice[] ep = rt.getForObject("http://localhost:8080/EPrice/services/person",
                EPrice[].class);
        return ep;
    }

    @PostMapping
    public void createEPrice(@RequestBody EPrice e) {
        repo.saveAndFlush(e);
    }
    
    @PutMapping("/{id}")
    public void updateEPrice(@PathVariable int id, @RequestBody EPrice e) {
        repo.saveAndFlush(e);
    }
    
    @DeleteMapping("/{id}")
    RequestInfo delete(@PathVariable int id){
        EPrice e = repo.findById(id).orElse(null);
        if (e == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found");
        repo.deleteById(id);
        return new RequestInfo();
    }  
}

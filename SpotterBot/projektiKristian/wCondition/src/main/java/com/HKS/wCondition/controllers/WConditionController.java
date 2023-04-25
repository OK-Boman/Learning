package com.HKS.wCondition.controllers;

import com.HKS.wCondition.Entities.RequestInfo;
import com.HKS.wCondition.Entities.Weather;
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
import com.HKS.wCondition.repositories.WeatherRepository;
import java.util.Comparator;

/**
 *
 * @author boman
 */
@RestController
@RequestMapping("/weathers")
public class WConditionController {

    @Autowired
    WeatherRepository repo;

    @GetMapping
    List<Weather> getAll(@RequestParam(defaultValue = "") String filter) {
        return repo.findFiltered("%" + filter + "%");
    }

    @GetMapping("/{id}")
    Weather get(@PathVariable int id) {
        Weather a = repo.findById(id).orElse(null);
        if (a == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found");
        }
        return a;
    }

//    //get weather with month and year
//    @GetMapping("/month/{month}/year/{year}")
//    Weather get(@PathVariable int month, @PathVariable int year) {
//        Weather a = repo.findByMonthAndYear(month, year);
//        if (a == null) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Foo Not Found");
//        }
//        return a;
//    }

    
    //get weather with highest temperature
    @GetMapping("/maxTemp")
    Weather getMaxTemp() {
        Weather a = repo.findAll().stream().max(Comparator.comparing(Weather::getTemperature)).get();
        return a;
    }

    //get weather with lowest temperature
    @GetMapping("/minTemp")
    Weather getMinTemp() {
        Weather a = repo.findAll().stream().min(Comparator.comparing(Weather::getTemperature)).get();
        return a;
    }

    //get weather with highest rain
    @GetMapping("/maxRain")
    Weather getMaxRain() {
        Weather a = repo.findAll().stream().max(Comparator.comparing(Weather::getRain)).get();
        return a;
    }

    //get weather with lowest rain
    @GetMapping("/minRain")
    Weather getMinRain() {
        Weather a = repo.findAll().stream().min(Comparator.comparing(Weather::getRain)).get();
        return a;
    }

    //get weather with highest wind
    @GetMapping("/maxWind")
    Weather getMaxWind() {
        Weather a = repo.findAll().stream().max(Comparator.comparing(Weather::getWind)).get();
        return a;
    }

    //get weather with lowest wind
    @GetMapping("/minWind")
    Weather getMinWind() {
        Weather a = repo.findAll().stream().min(Comparator.comparing(Weather::getWind)).get();
        return a;
    }

    @PostMapping
    Weather create(@RequestBody Weather a) {
        repo.saveAndFlush(a);
        return a;
    }

    @PutMapping("/{id}")
    Weather save(@PathVariable int id, @RequestBody Weather a) {
        repo.saveAndFlush(a);
        return a;
    }

    @DeleteMapping("/{id}")
    RequestInfo delete(@PathVariable int id) {
        Weather a = repo.findById(id).orElse(null);
        if (a == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found");
        }
        repo.deleteById(id);
        return new RequestInfo("Delete");
    }
}

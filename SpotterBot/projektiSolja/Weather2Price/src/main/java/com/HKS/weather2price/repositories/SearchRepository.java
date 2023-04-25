/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.HKS.Weather2Price.repositories;

import com.HKS.weather2price.entities.Search;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Solja
 */
public interface SearchRepository extends JpaRepository<Search,Integer> {
    
}

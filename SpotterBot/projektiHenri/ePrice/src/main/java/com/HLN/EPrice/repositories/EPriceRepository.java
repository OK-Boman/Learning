/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HLN.EPrice.repositories;

import com.HLN.EPrice.entities.EPrice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Henri
 */
public interface EPriceRepository extends JpaRepository<EPrice, Integer>{
    
    @Query ("SELECT e FROM EPrice e")
    List<EPrice> findFiltered (String filt);   
}

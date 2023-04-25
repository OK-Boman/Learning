/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HKS.weather2price.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Solja
 */
@Entity
public class Search {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @Column(name="weather_id")
    private Integer weatherId;
    @Column(name="price_id")    
    private Integer priceId;

    @Override
    public boolean equals(Object o){
        if (o.getClass() != Search.class) return false; 
        Search other = (Search)o;
        if (this.getId() != other.getId()) return false;
        if (!this.username.equals(other.username)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return getId();
    }   

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the weatherId
     */
    public Integer getWeatherId() {
        return weatherId;
    }

    /**
     * @param weatherId the weatherId to set
     */
    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    /**
     * @return the priceId
     */
    public Integer getPriceId() {
        return priceId;
    }

    /**
     * @param priceId the priceId to set
     */
    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }
    
}
 
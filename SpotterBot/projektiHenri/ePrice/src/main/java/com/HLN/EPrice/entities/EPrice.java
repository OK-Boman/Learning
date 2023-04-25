/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.HLN.EPrice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Henri
 */
@Entity
@Table(name = "price")
public class EPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double price;
    private Integer month;
    private Integer year;

    public EPrice() {
    }

    public EPrice(Integer id, Double price, Integer month, Integer year) {
        this.id = id;
        this.price = price;
        this.month = month;
        this.year = year;
    }
    
//    @Override
//    public boolean equals(Object o) {
//        if (o.getClass() != EPrice.class) {
//            return false;
//        }
//        EPrice other = (EPrice) o;
//        if (this.getId() != other.getId()) {
//            return false;
//        }
//        if (!this.id.equals(other.id)) {
//            return false;
//        }
//        return true;
//    }
    
    @Override
    public boolean equals (Object o){
        if (o.getClass() != EPrice.class ) return false;
        EPrice other=(EPrice)o;
        if(!this.month.equals(other.month))return false;
        if(!this.year.equals(other.year)) return false;
        if(!this.price.equals(other.price)) return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "MM/YYYY: "+ month + "."+ year+" Electric price: "+ price;
    }

    @Override
    public int hashCode() {
        return id;
    }

    /**
     * @return the id
     */
    
   
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    
    public double getPrice() {
        return price;
    }

    /**
     * @param price the name to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the month
     */
   
    public Integer getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    
    public Integer getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(Integer year) {
        this.year = year;
    }
}

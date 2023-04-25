package com.HKS.wCondition.Entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *
 * @author boman
 */

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double temperature;
    private Double wind;
    private Double rain;
    private Integer month;
    private Integer year;

    
    public Weather() {
    }

    
    public Weather(Integer id, Double temperature, Double wind, Double rain, Integer month, Integer year) {
        this.id = id;
        this.temperature = temperature;
        this.wind = wind;
        this.rain = rain;
        this.month = month;
        this.year = year;
    }

    
    @Override
    public boolean equals (Object o){
        if (o.getClass() != Weather.class ) return false;
        Weather other=(Weather)o;
//        if(this.id!=other.id)return false;
        if(!this.month.equals(other.month))return false;
        return true;
    }

    @Override
    public String toString() {
        return "MM/YYYY: "+month+"."+year+" Average weather: "+temperature+"°C / "+rain+"mm / "+wind+"m/s";
    }
    
    
    @Override
    public int hashCode(){
        return id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    public Double getTemperature() {
        return temperature;
    }

    public Integer getMonth() {
        return month;
    }

    public Double getRain() {
        return rain;
    }

    public Double getWind() {
        return wind;
    }

    public Integer getYear() {
        return year;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public void setWind(Double wind) {
        this.wind = wind;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.AirportZadar.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Korisnik
 */
@Entity
public class City {
    
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer city_id;
    
    @NotEmpty
    private String cityName;

    public City() {
    }

    public City(Integer city_id, String cityName) {
        this.city_id = city_id;
        this.cityName = cityName;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    
    
    
    
}

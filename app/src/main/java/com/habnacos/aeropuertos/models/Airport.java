package com.habnacos.aeropuertos.models;

public class Airport {
    String code;
    String name;
    String country;
    String city;
    String address;
    String latitude;
    String length;

    public Airport(String code, String name, String country, String city, String address, String latitude, String length) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.length = length;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}

package com.example.w8;

public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double size;
    private double price;

    public Bottle(String name, String manufacturer, double total_energy, double size, double price){
        this.name = name;
        this.size = size;
        this.price = price;
        this.total_energy = total_energy;
        this.manufacturer = manufacturer;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getManufacturer(){
        return manufacturer;
    }
    public void setManufacturer(String manufacturer){
        this.manufacturer = manufacturer;
    }
    public double getTotal_energy(){
        return total_energy;
    }
    public void  setTotal_energy(double total_energy){
        this.total_energy = total_energy;
    }
    public double getSize(){
        return size;
    }
    public void setSize(double size){
        this.size = size;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }
}

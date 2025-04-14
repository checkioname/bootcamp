package com.meli.concessionaria.models;

import java.util.Date;

public class Car {

    private int id;
    private String brand;
    private String model;

    private String color;
    private Date manufacuringDate;
    private int numberOfKilometers;
    private int doors;
    private double price;
    private String currency;
    private int countOfOwners;

    private int year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getManufacuringDate() {
        return manufacuringDate;
    }

    public void setManufacuringDate(Date manufacuringDate) {
        this.manufacuringDate = manufacuringDate;
    }

    public int getNumberOfKilometers() {
        return numberOfKilometers;
    }

    public void setNumberOfKilometers(int numberOfKilometers) {
        this.numberOfKilometers = numberOfKilometers;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getCountOfOwners() {
        return countOfOwners;
    }

    public void setCountOfOwners(int countOfOwners) {
        this.countOfOwners = countOfOwners;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Car() {}

    public Car(String brand) {
        this.brand = brand;
    }
    public Car(String brand, String model, Date manufacuringDate, int numberOfKilometers, int doors, double price, String currency, int countOfOwners) {
        this.brand = brand;
        this.model = model;
        this.manufacuringDate = manufacuringDate;
        this.numberOfKilometers = numberOfKilometers;
        this.doors = doors;
        this.price = price;
        this.currency = currency;
        this.countOfOwners = countOfOwners;
    }
}

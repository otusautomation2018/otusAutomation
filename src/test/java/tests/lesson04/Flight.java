package tests.lesson04;

import utils.helpers.DataHelpers;

public class Flight {
    public String number;
    public float price;
    public String airline;
    public String departureCity;
    public String destinationCity;

    public String departureCity() {
        String[] departureCities = {
                "Paris", "Philadelphia", "Boston", "Portland", "San Diego", "Mexico City", "São Paolo"};
        if(this.departureCity == null) {
            this.departureCity =  DataHelpers.getRandomArrayItem(departureCities);
        }
        return this.departureCity;
    }

    public String destinationCity() {
        String[] destinationCities = {
                "Buenos Aires", "Rome", "London", "Berlin", "New York", "Dublin", "Cairo"};
        if(this.destinationCity == null) {
            this.destinationCity =  DataHelpers.getRandomArrayItem(destinationCities);
        }
        return this.destinationCity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
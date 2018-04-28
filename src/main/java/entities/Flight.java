package entities;

import lombok.Data;
import utils.helpers.DataHelpers;

@Data
public class Flight {

    private float price;
    private String number;
    private String airline;
    private String departureCity;
    private String destinationCity;

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

//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    public float getPrice() {
//        return price;
//    }
//
//    public void setPrice(float price) {
//        this.price = price;
//    }
//
//    public String getAirline() {
//        return airline;
//    }
//
//    public void setAirline(String airline) {
//        this.airline = airline;
//    }
}

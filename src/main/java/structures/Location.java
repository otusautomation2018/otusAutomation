package structures;

import utils.helpers.DataHelpers;

public class Location {

    private String street;
    private String city;
    private String state;
    private String zipCode;

    public Location(
            final String newStreet,
            final String newCity,
            final String newState,
            final String newZipCode
    ) {
        this.street = newStreet;
        this.city = newCity;
        this.state = newState;
        this.zipCode = newZipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }


    public static class LocationBuilder {

        private String nestedStreet;
        private String nestedCity;
        private String nestedState;
        private String nestedZipCode;

        public LocationBuilder() {
            this.nestedStreet = setStreet();
            this.nestedCity = setCity();
            this.nestedState = setState();
            this.nestedZipCode = setZipCode();
        }

        private String setStreet() {
            if(nestedStreet == null) {
                return DataHelpers.getRandomArrayItem(DataHelpers.getStreets());
            } else {
                return nestedStreet;
            }
        }

        private String setCity() {
            if(nestedCity == null) {
                return DataHelpers.getRandomArrayItem(DataHelpers.getCities());
            } else {
                return nestedCity;
            }
        }

        private String setState() {
            if(nestedState == null) {
                return DataHelpers.getRandomArrayItem(DataHelpers.getStates());
            } else {
                return nestedState;
            }
        }

        private String setZipCode() {
            if(nestedZipCode == null) {
                return DataHelpers.generateZipCode();
            } else {
                return nestedZipCode;
            }
        }

        public LocationBuilder street(String newStreet) {
            this.nestedStreet = newStreet;
            return this;
        }

        public LocationBuilder city(String newCity) {
            this.nestedStreet = newCity;
            return this;
        }

        public LocationBuilder state(String newState) {
            this.nestedStreet = newState;
            return this;
        }

        public LocationBuilder zipCode(String newZipCode) {
            this.nestedStreet = newZipCode;
            return this;
        }

        public Location createLocation() {
            return new Location(
                    nestedStreet,
                    nestedCity,
                    nestedState,
                    nestedZipCode);
        }
    }
}

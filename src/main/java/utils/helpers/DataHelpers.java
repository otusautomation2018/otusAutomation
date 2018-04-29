package utils.helpers;

public class DataHelpers {

    public static int random(int min, int max){
        return min + (int)Math.round(Math.random()*(max-min));
    }

    public static String getRandomArrayItem(String[] array){
        return array[random(0, array.length-1)];
    }

    public static float discardUpTo2DecimalPlaces(float fl) {
        String[] splitedStr = String.valueOf(fl).split("\\.");
        String wholePart = splitedStr[0];
        String fractionPart = splitedStr[1].substring(0, 2);
        String resultStr = wholePart + "." + fractionPart;
        float result = Float.parseFloat(resultStr);

        return result;
    }

    public static String generateZipCode() {
        StringBuffer buf = new StringBuffer();
        for(int i=0; i<6;i++){
            buf.append(random(0,9));
        }
        return buf.toString();
    }

    public static String generateCardNumber(){
        StringBuffer buf = new StringBuffer();
        for (int i=0; i<4; i++){
            for (int k=0; k<4; k++){
                buf.append(random(0,9));
            }
            if (i!=3) buf.append(" ");
        }
        return buf.toString();
    }

    public static String[] getFirstnames(){
        String[] names = {
                "John", "Ben", "Steve", "Tom", "Roy", "Peter", "Bruce"
        };
        return names;
    }

    public static String[] getLastnames(){
        String[] lastnames = {
                "Johnson", "Stevenson", "Carlson", "Davidson", "Bush", "Clinton", "Trump"
        };
        return lastnames;
    }


    public static String[] getStates(){
        String[] states = {
                "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida",
                "Georgia", "Hawaii", "Idaho", "Illinois Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine",
                "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana Nebraska",
                "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota",
                "Ohio", "Oklahoma", "Oregon", "Pennsylvania Rhode Island", "South Carolina", "South Dakota", "Tennessee",
                "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
        };
        return states;
    }

    public static String[] getCities(){
        String[] cities = {
                "New York City", "Los Angeles", "Chicago", "Houston", "Philadelphia", "Phoenix", "San Antonio", "San Diego",
                "Dallas", "San Jose", "Austin", "Jacksonville", "Indianapolis", "San Francisco", "Columbus", "Fort Worth",
                "Charlotte", "Detroit", "El Paso", "Memphis", "Boston", "Seattle", "Denver"
        };
        return cities;
    }

    public static String[] getStreets(){
        String[] streets = {
                "Allen Street", "Ann Street", "Astor Place and Astor Row", "Barrow Street", "Bayard Street and Hester Street",
                "Beach Street", "Beak Street", "Bleecker Street", "Bogardus Place", "Bradhurst Avenue", "Broome Street",
                "Cabrini Boulevard", "Charles Street", "Charlton Street", "Christopher Street", "Colonel Robert Magaw Place",
                "Columbus Circle", "Cortlandt Street", "Delancey Street", "Detective Omar J. Edwards Way", "Dyckman Street",
                "Eldridge Street", "Forsyth Street", "Fulton Street", "Gay Street", "George Balanchine Way", "Great Jones Street",
                "Greene Street", "Henry Street (Manhattan)", "Horatio Street"
        };
        return streets;
    }

    public static String[] getTypesOfCards() {
        String[] typesOfCards = {"visa", "amex", "dinersclub"};
        return typesOfCards;
    }
}

package tests.helpers;

public class DataGenetaror {

    public static int random(int min, int max){
        return min + (int)Math.round(Math.random()*(max-min));
    }
}

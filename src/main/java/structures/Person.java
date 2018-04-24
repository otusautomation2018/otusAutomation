package structures;

public class Person {

    public String firstName;
    public String lastName;

    public String fullName(){
        return this.firstName + " " + this.lastName;
    }
}

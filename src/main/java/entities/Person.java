package entities;

import lombok.Data;
import utils.helpers.DataHelpers;

@Data
public class Person {

    private String firstName;
    private String lastName;

    public String fullName(){
        return this.firstName + " " + this.lastName;
    }

    public Person(final String newFirstName, final String newLastName) {
        this.firstName = newFirstName;
        this.lastName = newLastName;
    }

    public static class PersonBuilder {

         private String nestedFirstName;
         private String nestedLastName;

         public PersonBuilder() {

             this.nestedFirstName = setFirstName();
             this.nestedLastName = setLasttName();
         }

        private String setFirstName(){
            if(nestedFirstName == null) {
                return DataHelpers.getRandomArrayItem(DataHelpers.getFirstnames());
            } else {
                return nestedFirstName;
            }
        }

        private String setLasttName(){
            if(nestedLastName == null) {
                return DataHelpers.getRandomArrayItem(DataHelpers.getLastnames());
            } else {
                return nestedLastName;
            }
        }

        public PersonBuilder firstName(String newFirstName) {
            this.nestedFirstName = newFirstName;
            return this;
        }

        public PersonBuilder lastName(String newlastName) {
            this.nestedLastName = newlastName;
            return this;
        }

        public Person createPerson(){
             return new Person(nestedFirstName, nestedLastName);
        }
    }
}

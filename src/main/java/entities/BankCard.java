package entities;

import lombok.Data;
import utils.helpers.DataHelpers;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Data
public class BankCard {

    private String typeOfCard;
    private String cardNumber;
    private String nameOnCard;
    private String month;
    private String year;

    public BankCard(
            final String newTypeOfCard,
            final String newCardNumber,
            final String newNameOnCard,
            final String newMonth,
            final String newYear) {

        this.typeOfCard = newTypeOfCard;
        this.cardNumber = newCardNumber;
        this.nameOnCard = newNameOnCard;
        this.month = newMonth;
        this.year = newYear;
    }

//    public String getTypeOfCard() {
//        return typeOfCard;
//    }
//
//    public String getCardNumber() {
//        return cardNumber;
//    }
//
//    public String getNameOnCard() {
//        return nameOnCard;
//    }
//
//    public String getMonth() {
//        return month;
//    }
//
//    public String getYear() {
//        return year;
//    }

    public static class BankCardBuilder {
        private String nestedTypeOfCard;
        private String nestedCardNumber;
        private String nestedNameOnCard;
        private String nesntedMonth;
        private String nestedYear;

        public BankCardBuilder(final String nameOnCard) {

            this.nestedTypeOfCard = setTypeOfCard();
            this.nestedCardNumber = setCardNumber();
            this.nestedNameOnCard = nameOnCard;
            this.nesntedMonth = setMonth();
            this.nestedYear = setYear();
        }

        private String setTypeOfCard(){
            if(nestedTypeOfCard == null) {
                return DataHelpers.getRandomArrayItem(DataHelpers.getTypesOfCards());
            } else {
                return nestedTypeOfCard;
            }
        }

        private String setCardNumber(){
            if(nestedCardNumber == null) {
                return DataHelpers.generateCardNumber();
            } else {
                return nestedCardNumber;
            }
        }

        private String setMonth(){
            if(nesntedMonth == null) {
                return String.valueOf(DataHelpers.random(1, 12));
            } else {
                return nesntedMonth;
            }
        }

        private String setYear(){
            if(nestedYear == null) {
                GregorianCalendar cal = new GregorianCalendar();
                int yearMin = cal.get(Calendar.YEAR);
                int yearMax = yearMin + 5;
                return String.valueOf(DataHelpers.random(yearMin, yearMax));
            } else {
                return nestedYear;
            }
        }

        public BankCardBuilder typeOfCard(String newTypeOfCard) {
            this.nestedTypeOfCard = newTypeOfCard;
            return this;
        }

        public BankCardBuilder cardNumber(String newCardNumber) {
            this.nestedCardNumber = newCardNumber;
            return this;
        }

        public BankCardBuilder nameOnCard(String newNameOnCard) {
            this.nestedNameOnCard = newNameOnCard;
            return this;
        }

        public BankCardBuilder month(String newMonth) {
            this.nesntedMonth = newMonth;
            return this;
        }

        public BankCardBuilder year(String newYear) {
            this.nestedYear = newYear;
            return this;
        }

        public BankCard createBankCard() {
            return new BankCard(
                    nestedTypeOfCard,
                    nestedCardNumber,
                    nestedNameOnCard,
                    nesntedMonth,
                    nestedYear);
        }
    }
}

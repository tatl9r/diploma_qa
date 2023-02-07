package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    @Value
    public static class Card{
        String number;
        String month;
        String year;
        String cardHolderName;
        String cvc;
    }
    private static final Faker faker = new Faker(Locale.ENGLISH);
    public static String getApprovedNumber(){
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedNumber(){
        return "4444 4444 4444 4442";
    }

    public static String getRandomDigitNumber(int count){
        return faker.number().digits(count);
    }

    public static String getEmptyString(){
        return "";
    }

    public static String getSameNumber(){
        return "0000 0000 0000 0000";
    }

    public static String getMonth(int month){
        return LocalDate.now().plusMonths(month).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getFirstMonth(){
        return "01";
    }

    public static String getTwelveMonth(){
        return "12";
    }

    public static String getZeroMonth(){
        return "00";
    }

    public static String getThirteenMonth(){
        return "13";
    }

    public static String getOneNumberMonth(){
        return "1";
    }


    public static String getYear(int plusYear){
        return LocalDate.now().plusYears(plusYear).format(DateTimeFormatter.ofPattern("yy"));
    }


    public static String getOneNumberYear(){
        return "1";
    }


    public static String getCardHolderName() {
        return faker.name().name().toUpperCase();
    }

    public static String getThreeLettersName(){
        return "Sam";
    }

    public static String getHyphenatedName(){
        return "Anna Smirnova-Ivanova";
    }

    public static String getNameWithSpace(){
        return "Vasiliy A Gromov";
    }

    public static String getTwentyLettersName(){
        return "Stayhvbnmkloirfghtop";
    }

    public static String getTwoLettersName(){
        return "Am";
    }

    public static String getNumericalName(String numbers){
        return faker.numerify(numbers);
    }

    public static String getSpecialSymbolsName(){
        return "%*^)&*~@";
    }

    public static String getTwentyOneLettersName(){
        return "Stayhvbnmkloirfghtoph";
    }

    public static String getSpacesName(){
        return "     ";
    }


    public static String getCVC(){
        return faker.numerify("###");
    }

    public static String getTwoNumbersCVC(){
        return faker.numerify("#");
    }

    public static String getOneNumberCVC(){
        return faker.numerify("##");
    }

}

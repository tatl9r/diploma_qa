package Data;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataHelper {
    @Value
    public static class Card{
        String number;
        String month;
        String year;
        String cardHolderName;
        String cvc;
    }

    public static String getValidNumber(){
        return "5536927845986019";
    }

    public static String getFifteenDigitNumber(){
        return "553692784598601";
    }

    public static String getEmptyNumber(){
        return "";
    }

    public static String getSameNumber(){
        return "0000000000000000";
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

    public static String getEmptyMonth(){
        return "";
    }

    public static String getCurrentYear(){
        return "23";
    }

    public static String getPlusNineYear(){
        return "32";
    }

    public static String getLastYear(){
        return "22";
    }

    public static String getPlusTenYear(){
        return "33";
    }

    public static String getOneNumberYear(){
        return "1";
    }

    public static String getEmptyYear(){
        return "";
    }

    public static String getCardHolderName() {
        return fakerEng.name().name().toUpperCase();
    }
}

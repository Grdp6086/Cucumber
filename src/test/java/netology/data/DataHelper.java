package netology.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

public static CardInfo cardInfo(String card){
        return new CardInfo(card);

    }
public static AuthInfo getAuth(String login, String password){
        return new AuthInfo(login, password);
}


    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }
}

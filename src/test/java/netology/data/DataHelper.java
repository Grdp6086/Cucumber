package netology.data;

import lombok.Value;
public class DataHelper {



    public static TestIdInfo findTestIdByIndex(int index){
        String[] testIdArray = new String[]{
                "92df3f1c-a033-48e6-8390-206f6b1f56c0",
                "0f3f5c2a-249e-4c3d-8287-09f7a039391d"};
        var text = testIdArray[index];
        return new TestIdInfo(text);
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static CardInfo cardInfo(String card) {
        return new CardInfo(card);
    }


    public static AuthInfo getAuth(String login, String password) {
        return new AuthInfo(login, password);
    }


    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class TestIdInfo{
        String testId;
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

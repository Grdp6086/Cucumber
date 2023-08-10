package netology.page;

import com.codeborne.selenide.SelenideElement;
import netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement login = $("[data-test-id='login'] input");
    private final SelenideElement password = $("[data-test-id='password'] input");
    private final SelenideElement confirmButton = $("[data-test-id='action-login']");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        confirmButton.click();
        return new VerificationPage();
    }
}

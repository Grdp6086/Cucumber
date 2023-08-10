package netology.page;

import com.codeborne.selenide.SelenideElement;
import netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement code = $("[data-test-id='code'] input");
    private final SelenideElement confirmButton = $("[data-test-id='action-verify']");

    public VerificationPage() {
        code.should(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        code.setValue(verificationCode.getCode());
        confirmButton.click();
        return new DashboardPage();
    }
}

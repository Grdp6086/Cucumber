package netology.steps;

import io.cucumber.java.ru.*;
import netology.page.DashboardPage;
import netology.page.LoginPage;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;
import static netology.data.DataHelper.*;

public class TemplateSteps {
    private static DashboardPage dashboardPage;

    @Пусть("открыта страница с формой авторизации {string} и пользователь залогинен с именем {string} и паролем {string}")
    public void openAuthPage(String url, String name, String password) {
        var loginPage = open(url, LoginPage.class);
        var authInfo = getAuth(name, password);
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою карту {string}.")
    public void transferSecondToFirst(String amountCard, String card, String cardIndex) {
        var index = Integer.parseInt(cardIndex) - 1;
        var secondCard = cardInfo(card);
        dashboardPage.getCardBalance(index);
        dashboardPage.getCardBalance(secondCard);
        var amount = Integer.parseInt(amountCard);
        var transferPage = dashboardPage.selectCardToTransfer(getSecondCardTestId());
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCard);

    }


    @Тогда("тогда баланс его карты {string} из списка на главной странице должен стать {string} рублей.")
    public void verifyTransfer(String cardOffMoney, String balance) {
        var firstCard = cardInfo(cardOffMoney);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
        Assertions.assertEquals(Integer.parseInt(balance), actualBalanceFirstCard);
    }


}

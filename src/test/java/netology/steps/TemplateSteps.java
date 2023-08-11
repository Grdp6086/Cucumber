package netology.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ru.*;
import netology.data.DataHelper;
import netology.page.DashboardPage;
import netology.page.LoginPage;
import netology.page.VerificationPage;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static netology.data.DataHelper.*;

public class TemplateSteps {
    private DashboardPage dashboardPage;

    @Пусть("открыта страница с формой авторизации {string} и пользователь залогинен с именем {string} и паролем {string}")
    public void openAuthPage(String url, String name, String password) {
        var loginPage = open(url, LoginPage.class);
        var authInfo = getAuth(name, password);
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит {string} рублей на карту с номером {string} на свою карту {string}.")
    public void transferSecondToFirst(String amountCard, String card, String cardOffMoney) {
        var firstCard = cardInfo(cardOffMoney);
        var secondCard = cardInfo(card);
        var firstCardBalance = dashboardPage.getCardBalance(firstCard);
        var secondCardBalance = dashboardPage.getCardBalance(secondCard);
        var amount = Integer.parseInt(amountCard);
        var transferPage = dashboardPage.selectCardToTransfer(secondCard);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), secondCard);

    }


    @Тогда("тогда баланс его карты {string} из списка на главной странице должен стать {string} рублей.")
    public void verifyTransfer(String cardOffMoney, String balance) {
        var firstCard = cardInfo(cardOffMoney);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCard);
        Assertions.assertEquals(Integer.parseInt(balance), actualBalanceFirstCard);
    }


}

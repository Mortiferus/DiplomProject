package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DatabaseHelper;
import data.Card;
import data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentPageTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldPaymentByCardWithStatusApproved()  {
        DatabaseHelper.clearData();
        Card card= DataGenerator.getApprovedCard();
        val startPage=new StartPage();
        val paymentPage=startPage.goToPaymentPage();
        paymentPage.fillData(card);
        paymentPage.waitNotificationOk();
        assertEquals("APPROVED", DatabaseHelper.getPaymentStatus());
    }

    @Test
    void shouldPaymentByCardWithStatusDecline() {
        DatabaseHelper.clearData();
        Card card= DataGenerator.getDeclinedCard();
        val startPage=new StartPage();
        val paymentPage=startPage.goToPaymentPage();
        paymentPage.fillData(card);
        paymentPage.waitNotificationError();
        assertEquals("DECLINED",DatabaseHelper.getPaymentStatus());
    }


    @Test
    void shouldPaymentByIncorrectNumberCard() {
        Card card = DataGenerator.getIncorrectNumberCard();
        val startPage = new StartPage();
        val paymentPage = startPage.goToPaymentPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByExpiredMonthCard() {
        Card card = DataGenerator.getExpiredMonthCard();
        val startPage = new StartPage();
        val paymentPage = startPage.goToPaymentPage();
        paymentPage.fillData(card);
        assertEquals("Истек срок действия карты", paymentPage.getInputInvalid());
    }

    @ParameterizedTest
    @CsvSource(value = {"'00'", "'13'"})
    void shouldPaymentByIncorrectMonthCard(String month) {
        Card card = DataGenerator.getIncorrectMonthCard(month);
        val startPage = new StartPage();
        val paymentPage = startPage.goToPaymentPage();
        paymentPage.fillData(card);
        assertEquals("Неверно указан срок действия карты", paymentPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByIncorrectFormatMonthCard() {
        Card card = DataGenerator.getIncorrectFormatMonthCard();
        val startPage = new StartPage();
        val paymentPage = startPage.goToPaymentPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByExpiredYearCard() {
        Card card = DataGenerator.getExpiredYearCard();
        val startPage = new StartPage();
        val paymentPage = startPage.goToPaymentPage();
        paymentPage.fillData(card);
        assertEquals("Истёк срок действия карты", paymentPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByYearMore5Card() {
        Card card = DataGenerator.getYearMore5Card();
        val startPage = new StartPage();
        val paymentPage = startPage.goToPaymentPage();
        paymentPage.fillData(card);
        assertEquals("Неверно указан срок действия карты", paymentPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByIncorrectYearCard() {
        Card card = DataGenerator.getIncorrectYearCard();
        val startPage = new StartPage();
        val paymentPage = startPage.goToPaymentPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByIncorrectHolderCard() {
        Card card = DataGenerator.getIncorrectHolderCard();
        val startPage = new StartPage();
        val paymentPage = startPage.goToPaymentPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByIncorrectCvcCard() {
        Card card = DataGenerator.getIncorrectCVCCard();
        val startPage = new StartPage();
        val paymentPage = startPage.goToPaymentPage();
        paymentPage.fillData(card);
        assertEquals("Неверный формат", paymentPage.getInputInvalid());
    }

}

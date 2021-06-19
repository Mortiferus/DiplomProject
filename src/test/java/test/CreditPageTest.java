package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DatabaseHelper;
import data.Card;
import data.DataGenerator;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPageTest {
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
    void shouldCreditByCardWithStatusApproved()  {
        DatabaseHelper.clearData();
        Card card= DataGenerator.getApprovedCard();
        val startPage=new StartPage();
        val creditPage=startPage.goToCreditPage();
        creditPage.fillData(card);
        creditPage.waitNotificationOk();
        assertEquals("APPROVED",BD.getCreditStatus());
    }

    @Test
    void shouldCreditByCardWithStatusDecline() {
        DatabaseHelper.clearData();
        Card card= DataGenerator.getDeclinedCard();
        val startPage=new StartPage();
        val creditPage=startPage.goToCreditPage();
        creditPage.fillData(card);
        creditPage.waitNotificationError();
        assertEquals("DECLINED",BD.getCreditStatus());
    }


    @Test
    void shouldPaymentByIncorrectNumberCard() {
        Card card = DataGenerator.getIncorrectNumberCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByExpiredMonthCard() {
        Card card = DataGenerator.getExpiredMonthCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Истек срок действия карты", creditPage.getInputInvalid());
    }

    @ParameterizedTest
    @CsvSource(value = {"'00'", "'13'"})
    void shouldPaymentByIncorrectMonthCard(String month) {
        Card card = DataGenerator.getIncorrectMonthCard(month);
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверно указан срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByIncorrectFormatMonthCard() {
        Card card = DataGenerator.getIncorrectFormatMonthCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByExpiredYearCard() {
        Card card = DataGenerator.getExpiredYearCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Истёк срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByYearMore5Card() {
        Card card = DataGenerator.getYearMore5Card();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверно указан срок действия карты", creditPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByIncorrectYearCard() {
        Card card = DataGenerator.getIncorrectYearCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByIncorrectHolderCard() {
        Card card = DataGenerator.getIncorrectHolderCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }

    @Test
    void shouldPaymentByIncorrectCvcCard() {
        Card card = DataGenerator.getIncorrectCVCCard();
        val startPage = new StartPage();
        val creditPage = startPage.goToCreditPage();
        creditPage.fillData(card);
        assertEquals("Неверный формат", creditPage.getInputInvalid());
    }
}

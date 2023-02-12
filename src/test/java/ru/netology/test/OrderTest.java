package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.data.DataHelper;
import ru.netology.data.DbInteraction;
import ru.netology.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;

public class OrderTest {

    public OrderPage orderPage = new OrderPage();
    public DataHelper dataHelper = new DataHelper();

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @AfterEach
    void clearDataBase() {
        DbInteraction.clearDataBase();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentByCard() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentByCard() {
        orderPage.completePayFrom(DataGenerator.getDeclinedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                dataHelper::payDeclinedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithInvalidCardField() {
        orderPage.completePayFrom(DataGenerator.getRandomDigitNumber(15), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithEmptyCardField() {
        orderPage.completePayFrom(DataGenerator.getEmptyString(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::numberFieldFormatError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithSameCardField() {
        orderPage.completePayFrom(DataGenerator.getSameNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::numberFieldFormatError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentWithFirstMonth() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getFirstMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentWithTwelveMonth() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getTwelveMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithZeroMonth() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getZeroMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::monthFieldTermError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithThirteenMonth() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getThirteenMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::monthFieldTermError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithOneNumberMonth() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getOneNumberMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::monthFieldFormatError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithEmptyMonth() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getEmptyString(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::monthFieldFormatError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentWithCurrentYear() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentPlusNineYear() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(9), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentMinusOneYear() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(-1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::yearFieldMinusTermError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentPlusNineYear() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(9), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::yearFieldPlusTermError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithOneNumberYear() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getOneNumberYear(), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::yearFieldFormatError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithEmptyYear() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getEmptyString(), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::yearFieldFormatError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentWithThreeLettersName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getThreeLettersName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentWithHyphenatedName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getHyphenatedName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentWithSpacedName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getNameWithSpace(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentWithTwentyLettersName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getTwentyLettersName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithTwoLettersName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getTwoLettersName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                orderPage::cardHolderFieldFormatError,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithNumericalName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getNumericalName("#####"), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                orderPage::cardHolderFieldFormatError,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithSpecialSymbolsName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getSpecialSymbolsName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                orderPage::cardHolderFieldFormatError,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithTwentyOneLettersName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getTwentyOneLettersName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                orderPage::cardHolderFieldFormatError,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithSpacedName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getSpacesName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                orderPage::cardHolderFieldEmptyError,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithEmptyName() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getEmptyString(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                orderPage::cardHolderFieldEmptyError,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulPaymentWithThreeNumbersCVC() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::acceptAssertion,
                dataHelper::payApprovedStatusAssertion,
                dataHelper::payAcceptCountAssertion,
                dataHelper::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithOneNumberCVC() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getOneNumberCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::cvcFieldFormatError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithTwoNumbersCVC() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getTwoNumbersCVC());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::cvcFieldFormatError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentWithEmptyCVC() {
        orderPage.completePayFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getEmptyString());
        orderPage.continueClick();
        Assertions.assertAll(
                orderPage::denialAssertion,
                orderPage::cvcFieldFormatError,
                dataHelper::payDenialCountAssertion,
                dataHelper::orderDenialCountAssertion
        );
    }
}

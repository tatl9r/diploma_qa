package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.data.TestAssertions;
import ru.netology.data.DbInteraction;
import ru.netology.page.CreditPage;

import static com.codeborne.selenide.Selenide.open;


public class   CreditTest {

    public CreditPage creditPage = new CreditPage();
    public TestAssertions testAssertions = new TestAssertions();

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
    void shouldSuccessfulPaymentByCredit() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::creditApprovedStatusAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedPaymentByCredit() {
        creditPage.completeCreditFrom(DataGenerator.getDeclinedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                testAssertions::creditDeclinedStatusAssertion,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithInvalidCardField() {
        creditPage.completeCreditFrom(DataGenerator.getRandomDigitNumber(15), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::numberFieldFormatError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithEmptyCardField() {
        creditPage.completeCreditFrom(DataGenerator.getEmptyString(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::numberFieldFormatError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithSameCardField() {
        creditPage.completeCreditFrom(DataGenerator.getSameNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::denialAssertion,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulCreditPaymentWithFirstMonth() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getFirstMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::creditApprovedStatusAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulCreditPaymentWithTwelveMonth() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getTwelveMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::creditApprovedStatusAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithZeroMonth() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getZeroMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::monthFieldTermError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithThirteenMonth() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getThirteenMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::monthFieldTermError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithOneNumberMonth() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getOneNumberMonth(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::monthFieldFormatError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithEmptyMonth() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getEmptyString(), DataGenerator.getYear(1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::monthFieldFormatError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulCreditPaymentWithCurrentYear() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::payApprovedStatusAssertion,
                testAssertions::payAcceptCountAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulCreditPaymentPlusNineYear() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(9), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::creditApprovedStatusAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentMinusOneYear() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(-1), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::yearFieldMinusTermError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithOneNumberYear() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getOneNumberYear(), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::yearFieldFormatError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithEmptyYear() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getEmptyString(), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::yearFieldFormatError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulCreditPaymentWithThreeLettersName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getThreeLettersName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::creditApprovedStatusAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulCreditPaymentWithHyphenatedName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getHyphenatedName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::creditApprovedStatusAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulCreditPaymentWithSpacedName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getNameWithSpace(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::creditApprovedStatusAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulCreditPaymentWithTwentyLettersName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getTwentyLettersName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::creditApprovedStatusAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithTwoLettersName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getTwoLettersName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::cardHolderFieldFormatError,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithNumericalName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getNumericalName("#####"), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::cardHolderFieldFormatError,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithSpecialSymbolsName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getSpecialSymbolsName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::cardHolderFieldFormatError,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithTwentyOneLettersName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getTwentyOneLettersName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::cardHolderFieldFormatError,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithSpacedName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getSpacesName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::cardHolderFieldEmptyError,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithEmptyName() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getEmptyString(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::cardHolderFieldEmptyError,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldSuccessfulCreditPaymentWithThreeNumbersCVC() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::acceptAssertion,
                testAssertions::creditApprovedStatusAssertion,
                testAssertions::creditAcceptCountAssertion,
                testAssertions::orderAcceptCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithOneNumberCVC() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getOneNumberCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::cvcFieldFormatError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithTwoNumbersCVC() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getTwoNumbersCVC());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::cvcFieldFormatError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }

    @org.junit.jupiter.api.Test
    void shouldFailedCreditPaymentWithEmptyCVC() {
        creditPage.completeCreditFrom(DataGenerator.getApprovedNumber(), DataGenerator.getMonth(0), DataGenerator.getYear(0), DataGenerator.getCardHolderName(), DataGenerator.getEmptyString());
        creditPage.continueClick();
        Assertions.assertAll(
                creditPage::cvcFieldFormatError,
                testAssertions::creditDenialCountAssertion,
                testAssertions::orderDenialCountAssertion
        );
    }
}

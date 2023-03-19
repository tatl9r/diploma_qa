package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class CreditPage {
    private final MainPage mainPage = new MainPage();


    private final SelenideElement continueButton = Selenide.$(".form-field button");
    private final SelenideElement titleCard = Selenide.$x("//*[@id='root']/div/h3");


    private final SelenideElement cardNumber = Selenide.$("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement cardMonth = Selenide.$("[placeholder='08']");
    private final SelenideElement cardYear = Selenide.$("[placeholder='22']");

    private final SelenideElement cardHolderName = Selenide.$x("//*[.='Владелец'] //input");
    private final SelenideElement cardCVC = Selenide.$("[placeholder='999']");


    private final SelenideElement notificationTitleAccept = Selenide.$(".notification_status_ok");
    private final SelenideElement notificationContentAccept = Selenide.$(".notification_status_ok");

    private final SelenideElement notificationTitleDenial = Selenide.$(".notification_status_error");
    private final SelenideElement notificationContentDenial = Selenide.$(".notification_status_error");


    private final SelenideElement numberFieldError = Selenide.$x("//span[contains(text(),'Номер карты')]").parent().$(".input__sub");
    private final SelenideElement monthFieldError = Selenide.$x("//span[contains(text(),'Месяц')]").parent().$(".input__sub");
    private final SelenideElement yearFieldError = Selenide.$x("//span[contains(text(),'Год')]").parent().$(".input__sub");

    private final SelenideElement cardHolderFieldError = Selenide.$x("//span[contains(text(),'Владелец')]").parent().$(".input__sub");
    private final SelenideElement cvcFieldError = Selenide.$x("//span[contains(text(),'CVC/CVV')]").parent().$(".input__sub");


    public void completeCreditFrom(String number, String month, String year, String cardHolder, String cvc) {
        mainPage.clickCreditButton();
        titleCard.shouldBe(Condition.text("Кредит по данным карты"));
        cardNumber.setValue(number);
        cardMonth.setValue(month);
        cardYear.setValue(year);
        cardHolderName.setValue(cardHolder);
        cardCVC.setValue(cvc);
    }

    public void continueClick() {
        continueButton.click();
    }

    public void acceptAssertion() {
        notificationTitleAccept.shouldBe(Condition.text("Успешно"), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    public void denialAssertion() {
        notificationTitleDenial.shouldBe(Condition.text("Ошибка"), Duration.ofSeconds(15)).shouldBe(Condition.visible);
    }

    public void numberFieldFormatError() {
        numberFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }

    public void monthFieldFormatError() {
        monthFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }

    public void monthFieldTermError() {
        monthFieldError.shouldBe(Condition.text("Неверно указан срок действия карты"), Condition.visible);
    }

    public void yearFieldFormatError() {
        yearFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }

    public void yearFieldMinusTermError() {
        yearFieldError.shouldBe(Condition.text("Истёк срок действия карты"), Condition.visible);
    }

    public void cardHolderFieldEmptyError() {
        cardHolderFieldError.shouldBe(Condition.text("Поле обязательно для заполнения"), Condition.visible);
    }

    public void cardHolderFieldFormatError() {
        cardHolderFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }

    public void cvcFieldFormatError() {
        cvcFieldError.shouldBe(Condition.text("Неверный формат"), Condition.visible);
    }


}

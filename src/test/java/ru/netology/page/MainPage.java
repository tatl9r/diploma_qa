package ru.netology.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class MainPage {
    private final SelenideElement payButton = Selenide.$x("//*[@id='root']/div/button[1]");
    private final SelenideElement creditButton = Selenide.$x("//*[@id='root']/div/button[2]");

    public OrderPage clickPayButton() {
        payButton.click();
        return new OrderPage();
    }

    public OrderPage clickCreditButton() {
        creditButton.click();
        return new OrderPage();
    }

}

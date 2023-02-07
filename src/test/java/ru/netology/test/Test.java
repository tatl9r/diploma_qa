package ru.netology.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DbInteraction;
import ru.netology.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;

public class Test {
    public OrderPage orderPage = new OrderPage();

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @AfterEach
    void clearDataBase() {
        DbInteraction.clearDataBase();
    }
    
}

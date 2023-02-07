package ru.netology.data;

import org.junit.jupiter.api.Assertions;

public class DataHelper {
    public void payApprovedStatusAssertion() {

        String statusExpected = "APPROVED";
        String statusActual = DbInteraction.getDataBasePaymentStatus();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void payDeclinedStatusAssertion() {
        String statusExpected = "DECLINED";
        String statusActual = DbInteraction.getDataBasePaymentStatus();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void payAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DbInteraction.getDataBasePaymentCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void payDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DbInteraction.getDataBasePaymentCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DbInteraction.getDataBaseOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DbInteraction.getDataBaseOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void creditApprovedStatusAssertion() {
        String statusExpected = "APPROVED";
        String statusActual = DbInteraction.getDataBasePaymentStatus();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void creditDeclinedStatusAssertion() {
        String statusExpected = "DECLINED";
        String statusActual = DbInteraction.getDataBaseCreditStatus();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void creditAcceptCountAssertion() {
        long countExpected = 1;
        long countActual = DbInteraction.getDataBaseCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void creditDenialCountAssertion() {
        long countExpected = 0;
        long countActual = DbInteraction.getDataBaseCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

}

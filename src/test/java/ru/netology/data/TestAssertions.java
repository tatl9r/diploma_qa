package ru.netology.data;

import org.junit.jupiter.api.Assertions;

public class TestAssertions {
    public void payApprovedStatusAssertion() {

        var statusExpected = "APPROVED";
        var statusActual = DbInteraction.getDataBasePaymentStatus();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void payDeclinedStatusAssertion() {
        var statusExpected = "DECLINED";
        var statusActual = DbInteraction.getDataBasePaymentStatus();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void payAcceptCountAssertion() {
        var countExpected = 1;
        var countActual = DbInteraction.getDataBasePaymentCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void payDenialCountAssertion() {
        var countExpected = 0;
        var countActual = DbInteraction.getDataBasePaymentCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderAcceptCountAssertion() {
        var countExpected = 1;
        var countActual = DbInteraction.getDataBaseOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void orderDenialCountAssertion() {
        var countExpected = 0;
        var countActual = DbInteraction.getDataBaseOrderCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void creditApprovedStatusAssertion() {
        var statusExpected = "APPROVED";
        var statusActual = DbInteraction.getDataBaseCreditStatus();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void creditDeclinedStatusAssertion() {
        var statusExpected = "DECLINED";
        var statusActual = DbInteraction.getDataBaseCreditStatus();
        Assertions.assertEquals(statusExpected, statusActual);
    }

    public void creditAcceptCountAssertion() {
        var countExpected = 1;
        var countActual = DbInteraction.getDataBaseCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

    public void creditDenialCountAssertion() {
        var countExpected = 0;
        var countActual = DbInteraction.getDataBaseCreditCount();
        Assertions.assertEquals(countExpected, countActual);
    }

}

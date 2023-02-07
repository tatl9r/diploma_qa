package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class DbInteraction {

    @SneakyThrows
    public static String getDataBaseCreditStatus() {
        var sql = "SELECT status FROM credit_request_entity";
        var runner = new QueryRunner();
        String creditStatus;

        var conn = DriverManager.getConnection(System.getProperty("dbUrl"), "dbUser", "dbPass");

        creditStatus = runner.query(conn, sql, new ScalarHandler<>());

        return creditStatus;
    }

    @SneakyThrows
    public static String getDataBasePaymentStatus() {
        var sql = "SELECT status FROM payment_entity;";
        var runner = new QueryRunner();
        String paymentStatus;

        var conn = DriverManager.getConnection(System.getProperty("dbUrl"), "dbUser", "dbPass");

        paymentStatus = runner.query(conn, sql, new ScalarHandler<>());

        return paymentStatus;
    }

    @SneakyThrows
    public static long getDataBasePaymentCount() {
        var sql = "SELECT COUNT(id) as count FROM payment_entity;";
        var runner = new QueryRunner();
        long paymentCount;

        var conn = DriverManager.getConnection(System.getProperty("dbUrl"), "dbUser", "dbPass");

        paymentCount = runner.query(conn, sql, new ScalarHandler<>());

        return paymentCount;
    }

    @SneakyThrows
    public static long getDataBaseCreditCount() {
        var sql = "SELECT COUNT(id) as count FROM credit_request_entity;";
        var runner = new QueryRunner();
        long creditCount;

        var conn = DriverManager.getConnection(System.getProperty("dbUrl"), "dbUser", "dbPass");

        creditCount = runner.query(conn, sql, new ScalarHandler<>());

        return creditCount;
    }

    @SneakyThrows
    public static long getDataBaseOrderCount() {
        var sql = "SELECT COUNT(id) as count FROM order_entity;";
        var runner = new QueryRunner();
        long orderCount;

        var conn = DriverManager.getConnection(System.getProperty("dbUrl"), "dbUser", "dbPass");

        orderCount = runner.query(conn, sql, new ScalarHandler<>());

        return orderCount;
    }

    @SneakyThrows
    public static void clearDataBase() {

        var deleteOrder = "DELETE FROM order_entity;";
        var deletePayment = "DELETE FROM payment_entity;";
        var deleteCredit = "DELETE FROM credit_request_entity;";
        var runner = new QueryRunner();

        var conn = DriverManager.getConnection(System.getProperty("dbUrl"), "dbUser", "dbPass");

        runner.update(conn, deleteOrder);
        runner.update(conn, deletePayment);
        runner.update(conn, deleteCredit);

    }
}



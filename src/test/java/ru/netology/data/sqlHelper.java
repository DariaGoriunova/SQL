package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlHelper {
    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    public sqlHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static String getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var conn = getConn();
        return QUERY_RUNNER.query(conn, codeSQL, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var conn = getConn();
        QUERY_RUNNER.execute(conn, "DELETE FROM auth_codes");
        QUERY_RUNNER.execute(conn, "DELETE FROM card_transactions");
        QUERY_RUNNER.execute(conn, "DELETE FROM cards");
        QUERY_RUNNER.execute(conn, "DELETE FROM users");
    }

        @SneakyThrows
        public static void cleanAuthCodes() {
        var conn = getConn();
        QUERY_RUNNER.execute(conn, "DELETE FROM auth_codes");
    }
}

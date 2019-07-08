package dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {
    private static final Logger logger = Logger.getLogger(DbConnector.class);

    private static String dbPath = "jdbc:mysql://localhost:3306/usersdb" +
            "?verifyServerCertificate=false" +
            "&useSSL=false" +
            "&requireSSL=false" +
            "&useLegacyDatetimeCode=false" +
            "&amp" +
            "&serverTimezone=UTC";
    private static String name = "root";
    private static String password = "root";

    public static Connection connect() {
        Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(dbPath, name, password);
        } catch (Exception e) {
            logger.error("Can not connect to database " + dbPath);
        }
        return connect;
    }
}

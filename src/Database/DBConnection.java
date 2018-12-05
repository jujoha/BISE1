package Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/studienarbeit?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "toor";
    
    public static Connection getConnection() throws Exception{
        Class.forName(DBConnection.DRIVER);
        return DriverManager.getConnection( DBConnection.URL, DBConnection.USERNAME, DBConnection.PASSWORD);
    }
}

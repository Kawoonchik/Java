import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection getConnection() throws Exception {
        Properties properties = new Properties();
        try (InputStream input =  DatabaseConnection.class.getClassLoader().getResourceAsStream("database.properties")) {
            properties.load(input);
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}

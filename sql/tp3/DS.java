import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DS {
    private static String url;
    private static String driver;
    private static String login;
    private static String password;

    DS(){
        try{
            Properties p = new Properties();
            p.load(new FileInputStream("config.prop"));
            url = p.getProperty("url");
            login = p.getProperty("login");
            password = p.getProperty("password");
            driver = p.getProperty("driver");
            Class.forName(driver);
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        DS ds = new DS();
        return DriverManager.getConnection(url, login, password);
    }
}
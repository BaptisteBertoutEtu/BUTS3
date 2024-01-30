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

    private DS(){
        try{
            Properties p = new Properties();
            // p.load(new FileInputStream("/home/infoetu/baptiste.bertout.etu/cours/BUT_S3/back-end/tomcat/lib/config.prop"));
            p.load(new FileInputStream("C:\\Users\\bapti\\Documents\\cours\\BUT_S3\\back-end\\tomcat\\res\\configPerso.prop"));
            driver = p.getProperty("driver");
            Class.forName(driver);
            url = p.getProperty("url");
            login = p.getProperty("login");
            password = p.getProperty("password");
        }catch(Exception e){
            System.err.println(System.getProperty("user.dir"));
            System.err.println(e.getMessage());
        }
    }
 
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        DS ds = new DS();
        return DriverManager.getConnection(url, login, password);
    }
}
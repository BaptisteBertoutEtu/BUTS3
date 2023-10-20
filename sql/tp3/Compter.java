import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;


public class Compter {
    public static void main(String args[]) throws Exception {
        Properties p = new Properties();
        p.load(new FileInputStream("config.postgres.prop"));
        // enregistrement du driver
        Class.forName(p.getProperty("driver"));
        
        // connexion à la base
        String url = p.getProperty("url");
        String nom = p.getProperty("login");;
        String mdp = p.getProperty("password");;
        Connection con = DriverManager.getConnection(url, nom, mdp);
        
        Statement stmt = con.createStatement();
        String query = "select COUNT(*) AS resultat from CLIENT";
        ResultSet rs = stmt.executeQuery(query);
        
        System.out.println("Nombre de client");
        rs.next();
        int a = rs.getInt("resultat");
        System.out.println(a);
        
        // fermeture de la connexion
        con.close();
        System.out.println("All is ok !");
    }
}

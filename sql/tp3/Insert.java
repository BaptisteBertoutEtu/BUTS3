import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Insert {
    public static void main(String args[]) throws Exception {
        Properties p = new Properties();
        p.load(new FileInputStream("config.prop"));
        // enregistrement du driver
        Class.forName(p.getProperty("driver"));
        
        // connexion à la base
        String url = p.getProperty("url");
        String nom = p.getProperty("login");;
        String mdp = p.getProperty("password");;
        Connection con = DriverManager.getConnection(url, nom, mdp);
        
        // exécution de la requete
        for(int i=1 ; i<=1000 ; i++){
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into CLIENT " +
                "values("+ i +",'nom"+i+"', 'paul',10)");
        }
        
        // fermeture de la connexion
        con.close();
        System.out.println("All is ok !");
    }
}
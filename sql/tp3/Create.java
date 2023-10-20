import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Create {
    public static void main(String args[]) throws FileNotFoundException, IOException {
        try{
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
        Statement stmt = con.createStatement();
        stmt.executeUpdate("drop table CLIENT cascade");
        stmt.executeUpdate("create table CLIENT " +
            "(CNO int PRIMARY KEY, NOM varchar(10), PRENOM varchar(10), AGE int)");
        
        // fermeture de la connexion
        con.close();
        System.out.println("All is ok !");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

//export CLASSPATH=".:postgresql-42.6.0.jar"
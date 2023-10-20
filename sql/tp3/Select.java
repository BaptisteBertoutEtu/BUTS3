import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Select {
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
        String query = "select NOM,PRENOM,AGE from CLIENT";
        ResultSet rs = stmt.executeQuery(query);
        
        System.out.println("Liste des clients:");
        while (rs.next()) {
            String n = rs.getString("nom"); // col 1
            String p = rs.getString("prenom"); // col 2
            int a = rs.getInt("age"); // col 3
            System.out.println(n + " " + p + " " + a);
        }

        // fermeture de la connexion
        con.close();
        System.out.println("All is ok !");
    }
}
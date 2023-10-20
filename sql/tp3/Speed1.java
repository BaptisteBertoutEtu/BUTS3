import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Speed1 {
    public static void main(String args[]) throws Exception {
        try(Connection con = DS.getConnection();){
            double debut = System.currentTimeMillis();
            // exécution de la requete
            int i = 1;
            while(i<=100000){
                Statement stmt = con.createStatement();
                stmt.executeUpdate("insert into CLIENT " +
                    "values("+ i +",'nom"+i+"', 'paul')");
                i++;
            }
            
            // fermeture de la connexion
            con.close();
            System.out.println(System.currentTimeMillis() - debut);
            System.out.println("All is ok !");
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
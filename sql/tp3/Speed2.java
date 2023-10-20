import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Speed2 {
    public static void main(String args[]) throws Exception {
        try(Connection con = DS.getConnection();){
            double debut = System.currentTimeMillis();
            // exécution de la requete
            String query = "insert into client values (?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            for (int i = 1; i <= 10000; i++) {
                ps.setInt(1, i);
                ps.setString(2, "nom" + i);
                ps.setString(3, "paul");
                ps.executeUpdate();
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
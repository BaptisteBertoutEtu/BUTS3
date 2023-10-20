import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Transac2 {
    public static void main(String args[]) {
        Connection con = null;

        try{
            Properties p = new Properties();
            p.load(new FileInputStream("config.prop"));
            // enregistrement du driver
            Class.forName(p.getProperty("driver"));
            
            // connexion à la base
            String url = p.getProperty("url");
            String nom = p.getProperty("login");;
            String mdp = p.getProperty("password");;
            con = DriverManager.getConnection(url, nom, mdp);
            
            // exécution de la requete
            Statement stmt = con.createStatement();

            String pers = args[0];
            String prod = args[1];

            String r1 = "select qute from produit where prod='" + prod + "'";
            ResultSet rs = stmt.executeQuery(r1);
            rs.next();
            if (rs.getInt("qute") > 0) {
                String r3 = "update compte set solde=solde+100 where pers='" + pers + "'";
                stmt.executeUpdate(r3);
                Thread.sleep(10000);
                String r2 = "update produit set qute=qute-1 where prod='" + prod + "'";
                stmt.executeUpdate(r2);
                System.out.println("Achat effectué");
            } else {
                System.out.println("Achat impossible");
            }
            con.close();
            
        }catch(SQLException e){
            System.err.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.err.println(e.getMessage());
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
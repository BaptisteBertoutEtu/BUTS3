import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Transac1 {
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
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.execute("TRUNCATE TABLE client CASCADE");

            for(int i=1 ; i<=1000 ; i++){
                stmt = con.createStatement();
                stmt.executeUpdate("insert into CLIENT " +
                    "values("+ i +",'nom"+i+"', 'paul')");
            }

            for(int i=3000 ; i<=4000 ; i++){
                stmt = con.createStatement();
                stmt.executeUpdate("insert into CLIENT " +
                    "values("+ i +",'nom"+i+"', 'paul')");
            }
            
            System.out.println("All is okay");
            con.commit();
        }catch(Exception e){
            System.err.println(e.getMessage());
            try{
                con.rollback();
            }catch (SQLException e1) {
                System.err.println(e1.getMessage());
            }
        }finally{
            try {
                if(con != null){
                    con.close();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        
    }
}
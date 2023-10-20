import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Exo1 {

    public static void main(String[] args) throws Exception {
        
        Class.forName("org.postgresql.Driver");

        String url = "postgres://eptncfdl:LgGnaYNQSrpHf0713hKDduqmXwr1xczw@mouse.db.elephantsql.com/eptncfdl";
        String nom = "eptncfdl";
        String pass = "LgGnaYNQSrpHf0713hKDduqmXwr1xczw";

        Connection con = DriverManager.getConnection(url, nom, pass);

        Statement st = con.createStatement();
        String query = "select count(*) as result from personne where age between 20 and 40 and ville in ('Lille','Lens')";
        ResultSet rs = st.executeQuery(query);

        System.out.println("Il y a "+rs.getInt("result")+"personne(s) dans cette situation");
        con.close();
    }
}
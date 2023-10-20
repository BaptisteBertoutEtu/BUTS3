import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Exo2 {
    public static void main(String[] args) throws Exception{
        Class.forName("org.postgresql.Driver");

        String url = "postgres://eptncfdl:LgGnaYNQSrpHf0713hKDduqmXwr1xczw@mouse.db.elephantsql.com/eptncfdl";
        String nom = "eptncfdl";
        String pass = "LgGnaYNQSrpHf0713hKDduqmXwr1xczw";

        Connection con = DriverManager.getConnection(url, nom, pass);

        Statement st = con.createStatement();
        ResultSet rs1 = st.executeQuery("select distinct ville from personne order by asc;");

        String[] villes = new String[rs1.getFetchSize()];

        int i = 1;
        while (rs1.next()) {
            villes[i-1] = rs1.getString(i);
            i++;
        }

        PreparedStatement pst = con.prepareStatement("select pno,nom,prenom,age from personne where ville = '?';");
        ResultSet rs2 ;
        for (int j = 0; j < villes.length; j++) {
            pst.setString(1, villes[j]);
            rs2 = pst.executeQuery();
            System.out.println("<h2> "+villes[j]+" </h2>\n\t");
            while (rs2.next()) {
                System.out.println(""+rs2.getInt("pno")+"\t<td> "+rs2.getString("nom")+"\t<td> "+rs2.getString("prenom")+"\t<td>"+rs2.getString("age"));
            }
        }
    }
}

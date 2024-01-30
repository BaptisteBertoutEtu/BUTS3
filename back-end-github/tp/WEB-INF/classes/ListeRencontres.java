import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/liste-rencontre")
public class ListeRencontres extends HttpServlet{

    private static final String TDDEB = "<td>";
    private static final String TDFIN = "</td>";

    // resultat de la requete sql
    private ResultSet rs;
    
    // tous les parametres 
    private int num_match;
    private int eq1;
    private int eq2;
    private String jourString;
    private Date jour;
    private int sc1;
    private int sc2;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException{


        res.setContentType("text/html;");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>liste rencontre</title>" );
        out.println("<link href=\"css/style.css\" rel=\"stylesheet\">");
        out.println( "</head><body><center>" );
        out.println( "<h1>ListeRencontres</h1>" );
        out.println("<table>");
        
        // connection a la base de donnée sql
        try (Connection con = DS.getConnection()) {

            // statement permettant d'avoir l'ensemble des données sur les rencontres ordonnée par jour
            Statement st = con.createStatement();
            rs = st.executeQuery("select * from rencontres order by jour DESC ;");


            // affichage des entete du tableau
            out.println("<tr>");
            for (int i = 1; i <= 6; i++) {
                out.println("<th>"+rs.getMetaData().getColumnName(i)+"</th>");
            }
            out.println("</tr>");

            
            while (rs.next()) {
                // recuperation des données sql
                num_match = rs.getInt("num_match");
                eq1 = rs.getInt("eq1");
                eq2 = rs.getInt("eq2");
                jourString = rs.getString("jour");
                sc1 = rs.getInt("sc1");
                sc2 = rs.getInt("sc2");

                // formatage de la date de naissance du joueur
                java.util.Date date;
                if (jourString != null) {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(jourString);
                    jour = new Date(date.getTime());
                }
                else jour = null;

                out.println("<tr>");

                out.println(TDDEB+num_match+TDFIN);
                out.println(TDDEB+eq1+TDFIN);
                out.println(TDDEB+eq2+TDFIN);
                out.println(TDDEB+jour+TDFIN);
                out.println(TDDEB+sc1+TDFIN);
                out.println(TDDEB+sc2+TDFIN);

                out.println("</tr>");
            }


        } catch (Exception e) {
            out.println(e.getMessage());
        }


        out.println("</table>");
        out.println("</body></html>");

    }
}
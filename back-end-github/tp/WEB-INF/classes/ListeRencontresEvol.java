import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/liste-rencontreEvol")
public class ListeRencontresEvol extends HttpServlet{

    private static final String TDDEB = "<td>";
    private static final String TDFIN = "</td>";

    //resultat de la requete sql
    private ResultSet rs;
    
    //tous les parametres
    private int num_match;
    private int eq1;
    private int eq2;
    private String jourString;
    private Date jour;
    private int sc1;
    private int sc2;

    // liste contenant l'ensemble des nom d'entete du tableau pour les afficher
    private List<String> headerName ;;
    

    public ListeRencontresEvol(){
        headerName = new ArrayList<>();
        headerName.add("eq1");
        headerName.add("eq2");
        headerName.add("jour");
        headerName.add("sc1");
        headerName.add("sc2");
        headerName.add("supprimer");
        headerName.add("modifier");
    }

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
            
            // recuperation du parametre de tri
            String s = req.getParameter("tri");
            
            s = (s == null || s.equals("") || !headerName.contains(s) ? "eq1" : s);
            
            // statement permettant de selectonner les données des rencontres ordonnée par le parametre de tri
            Statement st = con.createStatement();
            rs = st.executeQuery("select * from rencontres order by "+s+" DESC;");
            
            
            // affichage des entete du tableau
            out.println("<tr>");
            for (int i = 0; i < headerName.size(); i++) {
                if(!headerName.get(i).equals("supprimer") && !headerName.get(i).equals("modifier")) out.println("<th><a href=\"./liste-rencontreEvo4?tri="+headerName.get(i)+"\">"+headerName.get(i)+"</a></th>");
                else out.println("<th>"+headerName.get(i)+"</th>");
            }
            out.println("</tr>");
            

            while (rs.next()) {

                // recuperation des données des rencontres
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
                
                
                out.println("<form method=post action=\"./supprimer-rencontre\">");
                out.println("<input name=\"num_match\" type=hidden value=\""+num_match+"\">");
                out.println(TDDEB+eq1+TDFIN);
                out.println(TDDEB+eq2+TDFIN);
                out.println(TDDEB+jour+TDFIN);
                out.println(TDDEB+sc1+TDFIN);
                out.println(TDDEB+sc2+TDFIN);
                out.println(TDDEB+"<input type=submit value=supprimer>"+TDFIN);
                out.println(TDDEB+"<a href=\"./modifier-rencontre?num_match="+num_match+"\">Modifier</a>"+TDFIN);

                out.println("</form>");
                out.println("</tr>");
            }


        } catch (Exception e) {
            out.println(e.getMessage());
        }


        out.println("</table>");
        out.println("</body></html>");

    }
}
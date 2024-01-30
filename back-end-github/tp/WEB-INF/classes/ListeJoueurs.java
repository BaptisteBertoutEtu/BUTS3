import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet-ListeJoueurs")
public class ListeJoueurs extends HttpServlet{
    private ResultSet rs;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        
        res.setContentType("text/html;");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>servlet listeJoueur</title>" );
        out.println( "</head><body><center>" );
        out.println( "<h1>Test ListeJoueur</h1>" );

        // connection a la base de donnée sql
        try (Connection con = DS.getConnection()){

            // statement permettant de connaitre l'ensemble des données trié par club
            Statement pst = con.createStatement();
            rs = pst.executeQuery("select * from joueurs order by club asc;");
            
        } catch (Exception e) {
            out.println(e.getMessage());
        }

        int num_joueur;
        String nom_joueur;
        String pays;
        String poste;
        int maillot;
        String date_naissance;
        int club;
        int salaire;

        try{
            // affichage du tableau
            out.println("<table>");

            // affichage des entete du tableau
            out.println("<tr>");
            for (int i = 1; i <= 8; i++) {
                out.println("<th>"+rs.getMetaData().getColumnName(i)+"</th>");
            }
            out.println("</tr>");

            // affichage de l'ensemble des données des joueurs
            while(rs.next()){
                out.println("<tr>");

                num_joueur = rs.getInt("num_joueur");
                nom_joueur = rs.getString("nom_joueur");
                pays = rs.getString("pays");
                poste = rs.getString("poste");
                maillot = rs.getInt("maillot");
                date_naissance = rs.getString("date_naissance");
                club = rs.getInt("club");
                salaire = rs.getInt("salaire");

                out.println("<td>"+num_joueur+"</td>");
                out.println("<td>"+nom_joueur+"</td>");
                out.println("<td>"+pays+"</td>");
                out.println("<td>"+poste+"</td>");
                out.println("<td>"+maillot+"</td>");
                out.println("<td>"+date_naissance+"</td>");
                out.println("<td>"+club+"</td>");
                out.println("<td>"+salaire+"</td>");
                
                
                out.println("</tr>");
            }
            out.println("</table>");
        }
        catch(Exception e){}

        out.println("<h1>C'est beau</h1>");
        out.println("</body></html>");

    }
    
}
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet-ListeJoueursClub")
public class ListeJoueursClub extends HttpServlet{

    private static final String TDDEB = "<td>";

    private static final String TDFIN = "</td>";
    private ResultSet rs;
    private ResultSet clubSet;
    private PreparedStatement pst2;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        
        res.setContentType("text/html;");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>servlet listeJoueur</title>" );
        out.println("<link href=\"css/style.css\" rel=\"stylesheet\">");
        out.println( "</head><body><center>" );
        out.println( "<h1>Test ListeJoueur</h1>" );

        //conntection a la base de données sql
        try(Connection con = DS.getConnection()){

            // statement permettant d'avoir l'ensemble des clubs de la base de donnée
            Statement pst = con.createStatement();
            clubSet = pst.executeQuery("select distinct club from joueurs;");

            // preparedstatement permettant d'avoir toutes les données des joueurs d'un club donné
            pst2 = con.prepareStatement("select * from joueurs where club = ?");
        } catch (Exception e) {
            out.println(e.getMessage());
        }

        int num_joueur;
        String nom_joueur;
        String pays;
        String poste;
        int maillot;
        String birthString;
        LocalDate date_naissance;
        int salaire;

        try{
            // tant qu'il y a des clubs
            while (clubSet.next()) {
                int club = clubSet.getInt("club");
                
                // affichage du numero du club
                out.println("<h2> Club : "+club+"</h2>");

                // mise a jour du preparedStatement
                pst2.setInt(1, club);
                // execution de la reqquete
                rs = pst2.executeQuery();

                //affichage du tableau
                out.println("<table>");

                // affichage des entete du tableau
                out.println("<tr>");
                for (int i = 1; i <= 8; i++) {

                    // affichage sans le club
                    if(i!=7) out.println("<th>"+rs.getMetaData().getColumnName(i)+"</th>");
                }
                out.println("</tr>");

                while(rs.next()){
                    
                    // recuperation des données
                    num_joueur = rs.getInt("num_joueur");
                    nom_joueur = rs.getString("nom_joueur");
                    pays = rs.getString("pays");
                    poste = rs.getString("poste");
                    maillot = rs.getInt("maillot");
                    birthString = rs.getString("date_naissance");
                    salaire = rs.getInt("salaire");

                    // affichage de la date de naissance + fond rouge si il a plus de 30 ans 
                    if(birthString != null) {
                        String[] birth = birthString.split("-");
                        date_naissance = LocalDate.of(Integer.parseInt(birth[0]),Integer.parseInt(birth[1]),Integer.parseInt(birth[2]));
                        if(ChronoUnit.YEARS.between(date_naissance,LocalDate.now())>=30) out.println("<tr class=\"birth-red\">");
                        else out.println("<tr>");
                    }
                    else {
                        date_naissance = null;
                        out.println("<tr>");
                    }
                    
                    


                    out.println(TDDEB+num_joueur+TDFIN);
                    out.println(TDDEB+nom_joueur+TDFIN);
                    out.println(TDDEB+pays+TDFIN);
                    out.println(TDDEB+poste+TDFIN);
                    out.println(TDDEB+maillot+TDFIN);
                    out.println(TDDEB+date_naissance+TDFIN);
                    out.println(TDDEB+salaire+TDFIN);
                    
                    
                    out.println("</tr>");
                    
                }
                out.println("</table>");
            }

        }
        catch(Exception e){
            out.println("</table>");
            out.println(e.getMessage());
        }

        out.println("</body></html>");

    }
    
}
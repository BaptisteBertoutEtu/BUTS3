import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/creer-rencontre")
public class CreerRencontre extends HttpServlet{
    
    // l'ensemble des parametres
    int numMatch;
    int eq1;
    int eq2;
    String jourString;
    int sc1;
    int sc2;
    
    java.util.Date jour;
    Date realJour;

    // resultat de la requete sql
    ResultSet rs;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // test de la connection vers la base de donnée
        try (Connection con = DS.getConnection()) {

            // PreparedStatement peremettant d'inserer une nouvelle rencontres
            PreparedStatement pst = con.prepareStatement("insert into rencontres values (?,?,?,?,?,?)");

            // statement peremttant de selectionner le num_match maximum de la base de donnée pour creer une nouvelle rencontre avec un num_match supérieur
            Statement st = con.createStatement();
            rs = st.executeQuery("select max(num_match) as max from rencontres;");
            
            // deefinition du nouveau numero de match
            while (rs.next()) {
                numMatch = rs.getInt("max") + 1;
            }

            // recuperation des différent parametre
            eq1 = Integer.parseInt(req.getParameter("eq1"));
            eq2 = Integer.parseInt(req.getParameter("eq2"));
            jourString = req.getParameter("jour");
            sc1 = Integer.parseInt(req.getParameter("sc1"));
            sc2 = Integer.parseInt(req.getParameter("sc2"));

            // formatage de la date pour l'insertion dans la base de donnée sql
            jour = new SimpleDateFormat("yyyy-MM-dd").parse(jourString);
            realJour = new Date(jour.getTime());

            // mise a jour du preparedStatement avec les parametres
            pst.setInt(1, numMatch);
            pst.setInt(2,eq1);
            pst.setInt( 3,eq2);
            pst.setDate(4,realJour);
            pst.setInt(5, sc1);
            pst.setInt(6,sc2);

            //redirection vers la page d'affichage des rencontres qd la mise a jour a ete effectué sinon on leve une exception
            if(pst.executeUpdate() <1) throw new Exception();
            else resp.sendRedirect("./liste-rencontre");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            // si une exception est levé, on redirige l'utilisateur vers la page se saisie
            resp.sendRedirect("./saisie.html");
        }
    }
}

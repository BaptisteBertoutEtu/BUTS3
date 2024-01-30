import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.*; // pour les servlets
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/JoueursDisponibles")
public class JoueursDisponibles extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        //gestion des parametres
        String poste = req.getParameter("poste");

        try(Connection con = DS.getConnection()){
            res.setContentType("text/html;charset=UTF-8");
            out.println("<!doctype html>");
            out.println("<head><title>servlet Test</title><link rel='stylesheet' href='style.css'></head><body><center> ");
            out.println("<h1>Joueur dispo</h1> ");
            out.println("<form method='post' action='#'><select name='poste'><option value='tous'>tous</option><option value='GAR'>GAR</option><option value='DEF'>DEF</option><option value='ATT'>ATT</option><option value='MIL'>MIL</option></select><input type='submit' value='envoyer'></form>");
            
            String query = "select num_joueur, maillot, nom_joueur from joueurs where poste=?";
            PreparedStatement ps = con.prepareStatement(query);
            

            //Traitement des parametres
            if(poste == null){
                ps.setString(1, "ATT");
            }else{
                ps.setString(1, poste);
            }
            ResultSet rs = ps.executeQuery();

            //Creation du tableau des joueurs
            out.println("<table><tr><th>maillot</th><th>nom joueur</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>"+rs.getInt("maillot")+"</td><td><a href='joueurs-choisis?joueur="+rs.getString("nom_joueur")+"'>"+rs.getString("nom_joueur")+"</a></td></tr>");
            }
            out.println("</table></body></html>");
        }catch(Exception e){
            out.println(e.getMessage());
        }
    }
}

    

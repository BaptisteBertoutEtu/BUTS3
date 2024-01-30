import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.sql.*;

import javax.naming.spi.DirStateFactory.Result;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/liste-joueur")
public class ListeJoueur extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException
    {PrintWriter out = res.getWriter();
        try{
            res.setContentType("text/html;charset=UTF-8");
            
            out.println("<!doctype html>");
            out.println("<head><title>servlet Test</title><link rel='stylesheet' href='style.css'></head><body><center>");
            ResultSet rs = getResult();
            out.println("<h1>Joueurs</h1>");
            int currentClub = -5;
            String red = "";
            while(rs.next())
            {
                int id = rs.getInt("num_joueur");
                String nom = rs.getString("nom_joueur");
                String pays = rs.getString("pays");
                String poste = rs.getString("poste");
                int maillot = rs.getInt("maillot");
                LocalDate date = LocalDate.parse(rs.getString("date_naissance"));
                int club = rs.getInt("club");
                int salaire = rs.getInt("salaire");
                if(LocalDate.now().compareTo(date) >= 30)
                {
                    red = "background: red;";
                }else
                {
                    red = "";
                }
                if(currentClub != club)
                {
                    currentClub = club;
                    out.println("</tbody></table>");
                    out.println("<h2>"+club+"</h2>");
                    out.println("<table><thead><tr><th>ID</th><th>Nom</th><th>Pays</th><th>Poste</th><th>Maillot</th><th>Date de naissance</th><th>Club</th><th>Salaire</th></tr></thead><tbody>");
                }
                out.println("<tr style='"+red+"'><td>"+id+"</td><td>"+nom+"</td><td>"+pays+"</td><td>"+poste+"</td><td>"+maillot+"</td><td>"+date+"</td><td>"+club+"</td><td>"+salaire+"</td></tr>");                
            }
            
            
            out.println("</body></html> ");
        }catch(Exception e)
        {
            out.println(e.getMessage());
        }
        
    }

    private ResultSet getResult() 
    {
        try (Connection conn = DS.getConnection()) {
            Statement st = conn.createStatement();
            return st.executeQuery("select * from joueurs order by club");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
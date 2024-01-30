import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.*; // pour les servlets
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/joueurs-choisis")
public class JoueursChoisis extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        //gestion des parametres
        String nomJoueur = req.getParameter("joueur");

            HttpSession session = req.getSession(true);
            ArrayList<String> listJoueur = (ArrayList<String>) req.getAttribute("joueurschoisis");

            out.println(listJoueur == null);

            if(listJoueur == null){
                listJoueur = new ArrayList<>();
            }

            if(nomJoueur != null && !nomJoueur.isBlank() && !listJoueur.contains(nomJoueur)){
                listJoueur.add(nomJoueur);
            }

            out.println(listJoueur.toString());
            session.setAttribute("joueurschoisis", listJoueur);
            
            res.setContentType("text/html;charset=UTF-8");
            out.println("<!doctype html>");
            out.println("<head><title>servlet Test</title><link rel='stylesheet' href='style.css'></head><body><center> ");
            out.println("<h1>Joueur choisi</h1> ");
            out.println("<table><tr><th>nom joueur</th></tr>");
            for(String joueur : listJoueur){
                out.println("<tr><td>"+joueur+"</td></tr>");
            }
            out.println("</table><a href='JoueursDisponibles'>retour</a></center></body></html>");
        
    }
}

    

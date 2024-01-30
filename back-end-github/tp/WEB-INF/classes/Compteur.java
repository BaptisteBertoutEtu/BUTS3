import java.io.*;
import jakarta.servlet.*; // pour les servlets
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/compteur")
public class Compteur extends HttpServlet{
    private int compteur = 0;
    
    public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        HttpSession session = req.getSession(true);
        Integer cpt = (Integer)session.getAttribute( "cpt" );
        cpt = Integer.valueOf( cpt == null ? 1 : cpt.intValue() + 1 );
        session.setAttribute( "cpt", cpt );
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        compteur += 1;
        out.println("<!doctype html>");
        out.println("<head><title>servlet Test</title><link rel='stylesheet' href='style.css'></head><body><center>");
        out.println("<p>Vous avez accédé "+cpt.intValue()+" fois à cette page sur les "+compteur+" accés au total.</p>");
    }
}

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/servlet-NouvelAn")

public class NouvelAn extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException 
    {
        res.setContentType("text/html;");
        PrintWriter out = res.getWriter();
        out.println("<!doctype html>");
        out.println("<head><title>servlet Test Nouvelle An</title><meta http-equiv=refresh content=1; ></head><body><center> ");
        out.println("<h1>Test de la Servlet Java Nouvelle An</h1> ");
        
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime aprilFirst = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);
        Duration delay = Duration.between(today, aprilFirst);
        long seconds = delay.get( ChronoUnit.SECONDS );

        out.println(seconds+" secondes");
        
        out.println("</table>");
        out.println("</body></html> ");
    }
}
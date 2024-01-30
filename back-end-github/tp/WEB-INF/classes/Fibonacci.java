import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/servlet-Fibonacci")

public class Fibonacci extends HttpServlet{
    public void service( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException{
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>servlet first</title>" );
        out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );
        out.println( "<h1>Test de ma Servlet Fibonacci</h1>" );
        int fib1 = 1;
        int fib2 = 0;
        int resultat;
        for(int i=0 ; i<30 ; i++){
            resultat = (fib1) + fib2;
            out.println(resultat + " ");
            fib1 = fib2;
            fib2 = resultat;
        }
        out.println( "<h2>Super ! ça marche</h2>" );
        out.println( "</center> </body>" );
    }
}
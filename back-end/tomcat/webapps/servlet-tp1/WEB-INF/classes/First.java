import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet-First")
public class First extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>servlet first</title>" );
        out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );
        out.println( "<h1>Test de ma Servlet</h1>" );
        out.println( "<h2>Super ! ça marche</h2>" );
        out.println( "</center> </body>" );
    }
}

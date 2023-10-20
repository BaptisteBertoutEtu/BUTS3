import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/servlet-Palette")
public class Palette extends HttpServlet{
    private String hexa = "0123456789ABCDEF";
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>servlet palette</title>" );
        out.println( "<META content=\"charset=UTF-8\"></head><body><center>" );
        out.println( "<h1>Test de ma Palette</h1>" );
        out.println("<table>");
        for (int i = 0; i < 16; i++) {
            out.println("<tr>");
            for (int j = 0; j < 16; j++) {
                out.println("<td style=\"background-color: #0"+hexa.charAt(i)+""+hexa.charAt(j)+";width: 20px; height: 20px;\"></td>");
            }
        }
        out.println("</table>");
        out.println("<h1>C'est beau</h1>");
        out.println("</body></html>");
    }

}

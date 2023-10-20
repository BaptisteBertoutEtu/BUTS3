import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet-Ascii")
public class Ascii extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>servlet ascii</title>" );
        out.println("<style> td{border: solid black 1px;}</style>");
        out.println( "</head><body><center>" );
        out.println( "<h1>Test ascii</h1>" );
        out.println("<table>");
        for (int i = 32 ; i < 256 ; i++) {
            out.println("<tr>");
            out.println("<td>"+i+"</td>");
            out.println("<td>"+(char)i+"</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("<h1>C'est beau</h1>");
        out.println("</body></html>");
    }
}
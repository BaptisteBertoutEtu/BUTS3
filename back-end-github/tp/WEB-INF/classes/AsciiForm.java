import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet-AsciiForm")
public class AsciiForm extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;");

        //permet d'ecrire sur la page html
        PrintWriter out = res.getWriter();

        String nbColString= "";
        int nbColInt = 1;

        // debut de la page html
        out.println( "<head><title>servlet ascii</title>" );
        out.println("<style> td{border: solid black 1px;} .tableau{display: flex; flex-direction: row; justify-content:space-around;}</style>");
        out.println( "</head><body><center>" );
        out.println( "<h1>Test ascii</h1>" );

        // affichage du formulaire qui permet de choisir le nombre de colonne avec un select html
        out.println("<form action=\"servlet-AsciiForm\"  method=\"post\">");
        out.println("<select name=\"nbCol\">" + //
                "  <option value=\"1\">1</option>" + //
                "  <option value=\"2\">2</option>" + //
                "  <option value=\"3\">3</option>" + //
                "  <option value=\"4\">4</option>" + //
                "  <option value=\"5\">5</option>" + //
                "  <option value=\"6\">6</option>" + //
                "  <option value=\"7\">7</option>" + //
                "</select>");
        out.println("<input type=\"submit\">");

        // verification du parametre
        nbColString = req.getParameter("nbCol");
        if(nbColString != null && !nbColString.isEmpty()) nbColInt = Integer.parseInt(nbColString);
        else nbColInt = 1;
        if(nbColInt>256 || nbColInt<0) nbColInt = 1;

        // calcul du nombre de caractere a affiché
        int nombreCarac = 256 - 32;
        int lastCarac = 32;
        int mod = nombreCarac/nbColInt;

        // affichage de la table ascii
        out.println( "<div class=\"tableau\">");
        for (int j = 0; j < nbColInt; j++) {
            out.println("<table>");
            for (int i = lastCarac ; i < lastCarac+mod ; i++) {
                out.println("<tr>");
                out.println("<td>"+i+"</td>");
                out.println("<td>"+(char)i+"</td>");
                out.println("</tr>");
            }
            lastCarac += mod;
            out.println("</table>");
        }
        out.println( "</div>");
        
        out.println("</body></html>");
    }
}
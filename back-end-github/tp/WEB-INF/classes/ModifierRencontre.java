import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/modifier-rencontre")
public class ModifierRencontre extends HttpServlet{

    ResultSet rs;
    private String param; 

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("text/html;");
        PrintWriter out = resp.getWriter();
        out.println( "<head><title>liste rencontre</title>" );
        out.println("<link href=\"style.css\" rel=\"stylesheet\">");
        out.println( "</head><body><center>" );
        out.println( "<h1>Modifier une rencontre</h1>" );

        try (Connection con = DS.getConnection()) {
            param = req.getParameter("num_match");

            Statement st = con.createStatement();
            rs = st.executeQuery("select * FROM rencontres WHERE num_match = "+Integer.parseInt(param)+";");

            rs.next();

            out.println("""
                <form action="./modif-rencontre-update" method="post">
                    <div>
            """);
            out.println("<input name=\"num_match\" type=hidden value=\""+rs.getInt("num_match")+"\">");
            out.println("<p>Equipe 1 :</p>");
            out.println("<input type=\"number\" name=\"eq1\" value="+rs.getInt("eq1")+"  required>");
            out.println("""
                </div>
                <div>
                    <p>Equipe 2 :</p>
                    """);
            out.println("<input type=\"number\" name=\"eq2\" value="+rs.getInt("eq2")+"  required>");
            out.println("""
                </div>
                <div>
                    <p>Date :</p>
                    """);
            out.println("<input type=\"date\" name=\"jour\" value="+rs.getDate("jour")+"  required>");
            out.println("""
                </div>
                <div>
                    <p>Score Equipe 1 :</p>
                    """);
            out.println("<input type=\"number\" name=\"sc1\" value="+rs.getInt("sc1")+"  required>");
            out.println("""
                </div>
                <div>
                    <p>Score Equipe 2 :</p>
                    """);
            out.println("<input type=\"number\" name=\"sc2\" value="+rs.getInt("sc2")+"  required>");
            out.println("""
                </div>
                <div>
                    """);
            out.println("<input type=\"submit\" value=\"MODIFIER\">");
            out.println("""
                </div>
                </form>
                    """);





        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        out.println("</body></html>");
    }
}

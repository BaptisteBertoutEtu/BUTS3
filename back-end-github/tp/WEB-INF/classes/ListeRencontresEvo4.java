import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/liste-rencontreEvo4")
public class ListeRencontresEvo4 extends HttpServlet{

    private static final String TDDEB = "<td>";
    private static final String TDFIN = "</td>";

    ResultSet rs;
    
    int num_match;
    int eq1;
    int eq2;
    String jourString;
    LocalDate jour;
    int sc1;
    int sc2;

    private List<String> headerName = new ArrayList<>();
    Cookie cookie;
    


    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException{
        
        headerName.clear();
        headerName.add("eq1");
        headerName.add("eq2");
        headerName.add("jour");
        headerName.add("sc1");
        headerName.add("sc2");
        headerName.add("supprimer");
        headerName.add("modifier");

        res.setContentType("text/html;");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>liste rencontre</title>" );
        out.println("<link href=\"style.css\" rel=\"stylesheet\">");
        out.println( "</head><body><center>" );
        out.println( "<h1>ListeRencontres</h1>" );
        out.println("<table>");
        
        try (Connection con = DS.getConnection()) {
            
            String s = req.getParameter("tri");
            
            s = (s == null || s.equals("") || !headerName.contains(s) ? "eq1" : s);
            
            Statement st = con.createStatement();
            
            rs = st.executeQuery("select * from rencontres order by "+s+" DESC;");
            
            
            out.println("<tr>");
            for (int i = 0; i < headerName.size(); i++) {
                if(!headerName.get(i).equals("supprimer") && !headerName.get(i).equals("modifier")) out.println("<th><a href=\"./liste-rencontreEvo4?tri="+headerName.get(i)+"\">"+headerName.get(i)+"</a></th>");
                else out.println("<th>"+headerName.get(i)+"</th>");
            }
            out.println("</tr>");
            
            while (rs.next()) {
                num_match = rs.getInt("num_match");
                eq1 = rs.getInt("eq1");
                eq2 = rs.getInt("eq2");
                jourString = rs.getString("jour");
                sc1 = rs.getInt("sc1");
                sc2 = rs.getInt("sc2");
                
                if(jourString != null) {
                    String[] birth = jourString.split("-");
                    jour = LocalDate.of(Integer.parseInt(birth[0]),Integer.parseInt(birth[1]),Integer.parseInt(birth[2]));
                }
                else {
                    jour = null;
                }
                
                out.println("<tr>");
                
                
                out.println("<form method=post action=\"./supprimer-rencontre\">");
                out.println("<input name=\"num_match\" type=hidden value=\""+num_match+"\">");
                out.println(TDDEB+eq1+TDFIN);
                out.println(TDDEB+eq2+TDFIN);
                out.println(TDDEB+jour+TDFIN);
                out.println(TDDEB+sc1+TDFIN);
                out.println(TDDEB+sc2+TDFIN);
                out.println(TDDEB+"<input type=submit value=supprimer>"+TDFIN);
                out.println(TDDEB+"<a href=\"./modifier-rencontre?num_match="+num_match+"\">Modifier</a>"+TDFIN);

                out.println("</form>");
                out.println("</tr>");
            }


        } catch (Exception e) {
            out.println(e.getMessage());
        }


        out.println("</table>");
        out.println("</body></html>");

    }
}
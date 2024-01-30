import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/servlet-Lecture")
public class Lecture extends HttpServlet{
    
    private String login;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(true);

        if(session.getAttribute("login")!=null){

            login = (String) session.getAttribute("login");

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<!doctype html>");
            out.println("<head><title>servlet Test table</title></head><body><center> ");
            out.println("<h1>Lecture</h1> ");
            out.println("<table>");

            try (Connection con = DS.getConnection()) {
                PreparedStatement pst = con.prepareStatement("select role from personne where login = ?;");
                pst.setString(1, login);
                ResultSet role = pst.executeQuery();
                role.next();

                
                
                if(role!=null && role.getString("role").equals("admin")){
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select * from personne;");

                    out.println("<tr>");
                    for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
                        out.println("<th>"+rs.getMetaData().getColumnName(i)+"</th>");
                    }
                    out.println("</tr>");

                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>"+rs.getString("role")+"</td>");
                        out.println("<td>"+rs.getString("login")+"</td>");
                        out.println("<td>"+rs.getString("mdp")+"</td>");
                        out.println("<td>"+rs.getString("nom")+"</td>");
                        out.println("<td>"+rs.getString("prenom")+"</td>");
                        out.println("<td>"+rs.getString("adresse")+"</td>");
                        out.println("<td>"+rs.getString("email")+"</td>");
                        out.println("<td>"+rs.getString("tel")+"</td>");
                        out.println("<td>"+rs.getDate("datenaiss")+"</td>");
                        out.println("</tr>");
                    }
                }
                else if(role!=null && role.getString("role").equals("util")){
                    PreparedStatement pst2 = con.prepareStatement("select * from personne where login = ? ;");
                    pst2.setString(1, login);
                    ResultSet rs = pst2.executeQuery();

                    out.println("<tr>");
                    for(int i=2;i<=rs.getMetaData().getColumnCount();i++){
                        out.println("<th>"+rs.getMetaData().getColumnName(i)+"</th>");
                    }
                    out.println("</tr>");

                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>"+rs.getString("login")+"</td>");
                        out.println("<td>"+rs.getString("mdp")+"</td>");
                        out.println("<td>"+rs.getString("nom")+"</td>");
                        out.println("<td>"+rs.getString("prenom")+"</td>");
                        out.println("<td>"+rs.getString("adresse")+"</td>");
                        out.println("<td>"+rs.getString("email")+"</td>");
                        out.println("<td>"+rs.getString("tel")+"</td>");
                        out.println("<td>"+rs.getDate("datenaiss")+"</td>");
                        out.println("</tr>");

                    }
                }
                
                
            } catch (Exception e) {
                out.println(e.getMessage());
            }

            out.println("</table>");
            out.println("</body></html>");
        }
        else{
            resp.sendRedirect("./login.html");
        }
    }
}

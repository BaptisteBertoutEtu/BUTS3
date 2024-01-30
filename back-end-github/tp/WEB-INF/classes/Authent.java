import java.io.IOException;
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


@WebServlet("/authent")
public class Authent extends HttpServlet {
    
    private String login;
    private String password; 

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        login = req.getParameter("l");
        password = req.getParameter("pass");

        
        try (Connection con = DS.getConnection()) {
            PreparedStatement pst = con.prepareStatement("select count(*) as count from personne where login = ? and mdp = ?;");
            pst.setString(1, login);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();


            HttpSession session = req.getSession(true);
            if(rs.next() && rs.getInt("count")==1){
                session.setAttribute("login", login);
                resp.sendRedirect("./menu");
            }
            else{
                resp.sendRedirect("./login.html");
            }




        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

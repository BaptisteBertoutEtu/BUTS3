import java.io.IOException;
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

@WebServlet("/supprimer-rencontre")
public class SupprimerRencontre extends HttpServlet{

    int rs;
    private String param; 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try (Connection con = DS.getConnection()) {
            param = req.getParameter("num_match");

            Statement st = con.createStatement();
            rs = st.executeUpdate("DELETE FROM rencontres WHERE num_match = "+Integer.parseInt(param)+";");

            if(rs <1) throw new Exception();
            else resp.sendRedirect("./liste-rencontreEvo4");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

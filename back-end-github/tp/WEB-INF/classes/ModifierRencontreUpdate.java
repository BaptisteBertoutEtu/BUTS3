import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/modif-rencontre-update")
public class ModifierRencontreUpdate extends HttpServlet{

    int rs;

    int num_match;
    int eq1;
    int eq2;
    String jourString;
    int sc1;
    int sc2;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try (Connection con = DS.getConnection()) {
            PreparedStatement st = con.prepareStatement("update rencontres set eq1 = ?, eq2 = ?, jour = ?, sc1 = ?,sc2 = ? where num_match = ?;");
            
            num_match = Integer.parseInt(req.getParameter("num_match"));
            eq1 = Integer.parseInt(req.getParameter("eq1"));
            eq2 = Integer.parseInt(req.getParameter("eq2"));
            jourString = req.getParameter("jour");
            sc1 = Integer.parseInt(req.getParameter("sc1"));
            sc2 = Integer.parseInt(req.getParameter("sc2"));

            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(jourString);
            Date d = new Date(date.getTime());

            st.setInt(1, eq1);
            st.setInt(2, eq2);
            st.setDate(3, d);
            st.setInt(4, sc1);
            st.setInt(5, sc2);
            st.setInt(6, num_match);

            rs = st.executeUpdate();
            

            if(rs <1) throw new Exception("taz mere");
            else resp.sendRedirect("./liste-rencontreEvo4");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

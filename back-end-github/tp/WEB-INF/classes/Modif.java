import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/servlet-Modif")
public class Modif extends HttpServlet{

    private HttpSession session;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession(true);

        if(session.getAttribute("login")!=null){


            resp.setContentType("text/html;");
            PrintWriter out = resp.getWriter();
            out.println( "<head><title>liste rencontre</title>" );
            out.println("<link href=\"style.css\" rel=\"stylesheet\">");
            out.println( "</head><body><center>" );
            out.println( "<h1>Modifier mes données</h1>" );

            String login = (String) session.getAttribute("login");

            try (Connection con = DS.getConnection()) {

                PreparedStatement pst = con.prepareStatement("select role from personne where login = ?;");
                pst.setString(1, login);
                ResultSet role = pst.executeQuery();
                role.next();
                
                
                
                if(role!=null && role.getString("role").equals("admin")){
                    Statement st1 = con.createStatement();
                    ResultSet rs;
                    rs = st1.executeQuery("select * FROM personne;");

                    out.println("""
                        <form action="./servlet-Modif" method="post">
                        """);

                    while (rs.next()) {
                        out.println("<input type=\"text\" name=\"login\" value="+rs.getString("login")+"  required>");
                        out.println("<input type=\"text\" name=\"password\" value="+rs.getString("mdp")+"  required>");
                        out.println("<input type=\"text\" name=\"nom\" value="+rs.getString("nom")+"  required>");
                        out.println("<input type=\"text\" name=\"prenom\" value="+rs.getString("prenom")+"  required>");
                        out.println("<input type=\"text\" name=\"adresse\" value="+rs.getString("adresse")+"  required>");
                        out.println("<input type=\"email\" name=\"email\" value="+rs.getString("email")+"  required>");
                        out.println("<input type=\"tel\" pattern=\"[0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2}\" name=\"tel\" value="+rs.getString("tel")+"  required>");
                        out.println("<span>format : 01 23 45 67 89</span>");
                        out.println("<input type=\"date\" name=\"datenaiss\" value="+rs.getString("datenaiss")+"  required>");
                        out.println("<input type=\"submit\" value=\"MODIFIER\">");
                    }

                    out.println("""
                        </form>
                    """);
                }
                else if(role!=null && role.getString("role").equals("util")){
                    //TODO: 0222
                    PreparedStatement st2 = con.prepareStatement("select * FROM personne WHERE login = ?;");
                    ResultSet rs;
                    st2.setString(1, login);
                    rs = st2.executeQuery();

                    rs.next();

                    out.println("""
                        <form action="./servlet-Modif" method="post">
                        """);

                    
                    out.println("<input type=\"text\" name=\"login\" value="+rs.getString("login")+"  required>");
                    out.println("<input type=\"text\" name=\"password\" value="+rs.getString("mdp")+"  required>");
                    out.println("<input type=\"text\" name=\"nom\" value="+rs.getString("nom")+"  required>");
                    out.println("<input type=\"text\" name=\"prenom\" value="+rs.getString("prenom")+"  required>");
                    out.println("<input type=\"text\" name=\"adresse\" value="+rs.getString("adresse")+"  required>");
                    out.println("<input type=\"email\" name=\"email\" value="+rs.getString("email")+"  required>");
                    out.println("<input type=\"tel\" pattern=\"[0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2} [0-9]{2}\" name=\"tel\" value="+rs.getString("tel")+"  required>");
                    out.println("<span>format : 01 23 45 67 89</span>");
                    out.println("<input type=\"date\" name=\"datenaiss\" value="+rs.getString("datenaiss")+"  required>");
                    out.println("<input type=\"submit\" value=\"MODIFIER\">");
                    

                    out.println("""
                        </form>
                    """);
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            out.println("</body></html>");
        }
        else{
            resp.sendRedirect("./login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login;
        String password;
        String nom;
        String prenom;
        String adresse;
        String email;
        String tel;
        String datenaiss;


        try (Connection con = DS.getConnection()) {
            
            PreparedStatement st = con.prepareStatement("update personne set login = ?, mdp = ?, nom = ?, prenom = ?, adresse = ?, email = ?, tel = ?, datenaiss = ?;");
            int rs;

            login = req.getParameter("login");
            password = req.getParameter("password");
            nom = req.getParameter("nom");
            prenom = req.getParameter("prenom");
            adresse = req.getParameter("adresse");
            email = req.getParameter("email");
            tel = req.getParameter("tel");
            datenaiss = req.getParameter("datenaiss");


            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datenaiss);
            Date d = new Date(date.getTime());

            st.setString(1, login);
            st.setString(2, password);
            st.setString(3, nom);
            st.setString(4, prenom);
            st.setString(5, adresse);
            st.setString(6, email);
            st.setString(7, tel);
            st.setDate(8, d);

            rs = st.executeUpdate();
            

            if(rs <1) throw new Exception("pas d'update");
            else resp.sendRedirect("./menu.html");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

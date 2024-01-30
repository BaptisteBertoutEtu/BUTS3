import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mon-equipe")
public class MonEquipe extends HttpServlet{

    private ResultSet resultSet;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        
        try (Connection con = DS.getConnection()) {
            Statement st = con.createStatement();
            resultSet = st.executeQuery("select distinct num_equipe from equipes;");

            out.println("""
                <!DOCTYPE html>
                <html lang="fr">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Saisie</title>
                </head>
                <body>
                    <form action="./liste-rencontreEvo4" method="post">
                        <div>
                            <p>Choisissez votre équipe :</p>
                        </div>
                        <div>
                            <select name="equipe">
                                <option value=""selected>-- Choisissez une équipe --</option>
                """);

            out.println("avant");

            while (resultSet.next()) {
                out.println("<option value=\""+resultSet.getInt("num_equipe")+"\">"+resultSet.getInt("num_equipe")+"</option>");
            }


            out.println("apres");
            out.println("""
                            </select>
                        </div>
                        <div>
                            <input type="submit" value="SUBMIT">
                        </div>
                    </form>
                </body>
                </html>
                    """);


        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
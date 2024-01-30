import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/menu")
public class Menu extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("text/html;");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(true);

        if(session.getAttribute("login")!=null){
            out.println("""
                <!DOCTYPE html>
                <html lang="fr">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>MENU</title>
                </head>
                <body>
                    <h1>MENU</h1>
                    <a href="./servlet-Lecture" >Lecture</a>
                    <a href="./servlet-Modif" >Modifier</a>
                    <a href="./servlet-Deconnecte" >Deconnection</a>
                </body>
                </html>
                    """);
        }
        else{
            resp.sendRedirect("./login.html");
        }

    }
}

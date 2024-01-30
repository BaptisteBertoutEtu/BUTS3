import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/servlet/Maj")
public class Maj extends HttpServlet 
{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	
	/* NE PAS MODIFIER (Sauf indication)*/
	out.println("<!DOCTYPE html><html lang='fr'>");
	out.println("<head><meta charset='utf-8'><meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'>");
	
		/* Titre de la page HTML */
	out.println("<title>Administration</title>");
		/* **************** */
	
	out.println("<!-- Bootstrap core CSS --><link href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css' rel='stylesheet'>");
	
	out.println("</head>");
	out.println("<body>");
	
	// authentifie ?
	HttpSession session = req.getSession(true);
	Personne p = (Personne)session.getAttribute("login");
	if (p==null) res.sendRedirect("Deconnect");
	
	out.println("<div class='container'>");
	
		out.println("<div class='page-header'>");
			out.println("<h1>Modifier vos informations</h1>");
		out.println("</div>");
	

			
				out.println("<form class='form-horizontal' method='get' action='Maj2'>");
					out.println("<div class='form-group row'>");
						out.println("<label for='inputLogin' class='col-2 control-label'>Login</label>");
						out.println("<div class='col-10'>");
							out.println("<input type='text' class='form-control' id='inputLogin' name='login' value='"+p.login+"'>");
						out.println("</div>");
					out.println("</div>");
					
					out.println("<div class='form-group row'>");
						out.println("<label for='inputPassword' class='col-2 control-label'>Mot de passe</label>");
						out.println("<div class='col-10'>");
							out.println("<input type='text' class='form-control' id='inputPassword' name='mdp' value='"+p.mdp+"'>");
							out.println("</div>");
					out.println("</div>");
					
					out.println("<div class='form-group row'>");
						out.println("<label for='inputNom' class='col-2 control-label'>Nom</label>");
						out.println("<div class='col-10'>");
							out.println("<input type='text' class='form-control' id='inputNom' name='nom' value='"+p.nom+"'>");
						out.println("</div>");
					out.println("</div>");
				
					out.println("<div class='form-group row'>");
						out.println("<label for='inputPrenom' class='col-2 control-label'>Prénom</label>");
						out.println("<div class='col-10'>");
							out.println("<input type='text' class='form-control' id='inputPrenom' name='prenom' value='"+p.prenom+"'>");
						out.println("</div>");
					out.println("</div>");
			
					out.println("<div class='form-group row'>");
						out.println("<label for='inputAdresse' class='col-2 control-label'>Adresse</label>");
						out.println("<div class='col-10'>");
							out.println("<input type='text' class='form-control' id='inputAdresse' name='adresse' value='"+p.adresse+"'>");
						out.println("</div>");
					out.println("</div>");
					
					out.println("<div class='form-group row'>");
						out.println("<div class='col-offset-2 col-10'>");
							out.println("<button type='submit' class='btn btn-primary'>Mettre à jour</button>");
						out.println("</div>");
					out.println("</div>");
				out.println("</form>");
				out.println("<a href='Menu'><button class='btn btn-default'>Retour au menu</button></a>");
			

	out.println("</div>");
    
	out.println("</body></html>");
    
    }
    
}

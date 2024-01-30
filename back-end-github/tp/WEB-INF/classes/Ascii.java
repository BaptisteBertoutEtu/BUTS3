import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/servlet-Ascii")

public class Ascii extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException 
    {
        res.setContentType("text/html;");
        PrintWriter out = res.getWriter();
        out.println("<!doctype html>");
        out.println("<head><title>servlet Test table</title></head><body><center> ");
        out.println("<h1>Test de la Servlet Java</h1> ");
        String para = req.getParameter("nbCol");
        int nbcol;
        if(para == null || para.isEmpty()){
            nbcol = 1;
        }else{
            nbcol = Integer.parseInt(para);
        }
        if(nbcol <= 0 || nbcol > 256-32){
            nbcol = 1;
        }
        int dernierCarac = 32;
        int nbCaracCol = (256-32) / nbcol;
        out.println("Nombre de colone= " + nbcol + "<br>");
        out.println("<table>");
        for(int i=dernierCarac ; i<nbCaracCol+32 ; i++){
            out.println("<tr>");
            for(int y = 0 ; y < nbcol ; y++){
                out.println("<td>" + (i+(nbCaracCol*y)) +" " + (char) (i+(nbCaracCol*y)) + "</td>");
            }
            out.println("</tr");
        }
        
        out.println("</table>");
        out.println("</body></html> ");
    }
}
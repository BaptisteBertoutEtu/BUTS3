import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/servlet-Palette")

public class Palette extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException 
    {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<!doctype html>");
        out.println("<head><title>servlet Test table</title></head><body><center> ");
        out.println("<h1>Test de la Servlet Java</h1> ");
        out.println("<table>");
        String para = req.getParameter("r");
        int r;
        if(para == null || para.isEmpty()){
            r = 0;
        }else{
            r = Integer.parseInt(para);
        }
        if(r < 0 || r > 15){
            r = 0;
        }
        out.println("Palette r = " + r);
        String hexa = "0123456789ABCDEF";
        for(int i=0;i<16;i++)
        {
            out.println("<tr>");
            for(int j=0;j<16;j++)
            {
                out.println("<td style='background: #"+hexa.charAt(i)+hexa.charAt(r)+hexa.charAt(j)+";color: #"+hexa.charAt(i)+hexa.charAt(r)+hexa.charAt(j)+"'>H</td>");
            }
            out.println("test");
            out.println("</tr>");
        }
        out.println("</table>");
        for(int i = 0 ; i<16 ; i++){
            out.println("<a href=servlet-Palette?r="+ i +">Vers r = "+ i + "</a><br>");
        }
        out.println("</body></html> ");
    }
}
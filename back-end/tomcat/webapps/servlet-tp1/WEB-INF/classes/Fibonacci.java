import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet-Fibonacci")
public class Fibonacci extends HttpServlet{
    private int fnmoins1 = 1;
    private int fnmoins2 = 0; 
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>servlet fibonacci</title>" );
        out.println( "<META content=\"charset=UTF-8\"></head><body>" );
        out.println( "<h1>Test de ma Servlet</h1>" );
        fnmoins1 = 1;
        fnmoins2 = 0;
        StringBuilder sb = new StringBuilder(""+fnmoins1+" ");
        for (int i = 2; i < 31; i++) {
            sb.append(getFibonacci()+" ");
        }
        out.println( "<h2>Super ! ça marche</h2>" );
        out.println(sb.toString());
        out.println( "</body>" );
    }

    private int getFibonacci(){
        int temp = fnmoins1 + fnmoins2;
        fnmoins2 = fnmoins1;
        fnmoins1 = temp;
        return temp;
    }

}

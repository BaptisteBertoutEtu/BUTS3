import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servlet-NouvelAn")
public class NouvelAn extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;");
        PrintWriter out = res.getWriter();
        out.println( "<head><title>servlet ascii</title>" );
        out.println("<meta http-equiv=refresh content=1;>");
        out.println( "</head><body><center>" );
        out.println( "<h1>Test nouvel an</h1>" );
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime nouvelan = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0);
        Duration delay = Duration.between(today, nouvelan);
        long seconds = delay.get( ChronoUnit.SECONDS );
        out.println("temps avant nouvel an : "+seconds);
        out.println("<h1>C'est beau</h1>");
        out.println("</body></html>");
    }
}

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.*;

@WebServlet("/game/genres")
public class GameGenreServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        res.setHeader("Access-Control-Max-Age", "3600");
        DbConnection db = new DbConnection();
        Helper helper = new Helper();
        try (
            Connection conn = db.connect();         
            ) {
            //helper.addHeader(res);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            PrintWriter out = res.getWriter();
            out.print(db.getGenres());
            out.close();
            res.setStatus(HttpServletResponse.SC_OK);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }


    }
}

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
        DbConnection db = new DbConnection();
        Helper helper = new Helper();
        try (
            Connection conn = db.connect();         
            ) {
            helper.addHeader(res);
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

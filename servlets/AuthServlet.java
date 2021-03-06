import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.*;
// import javax.servlet.http.Cookie;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        Helper helper = new Helper();
        DbConnection db = new DbConnection();

        try (
            Connection conn = db.connect();         
            ) {
            helper.addHeader(res);
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            PrintWriter out = res.getWriter();

            String reqBody = helper.getBody(req);
            JsonObject obj = new Gson().fromJson(reqBody, JsonObject.class);
            String token = obj.get("token").getAsString();
            System.out.println(token);

            JsonObject userobj = db.getUserByToken(token);
            if (userobj != null) {
                userobj.addProperty("token", token);
                System.out.println(userobj);
                out.print(userobj);
            }
            out.close();
            res.setStatus(HttpServletResponse.SC_OK);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }


    }
}

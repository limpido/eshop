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
            String token = obj.get("token").toString();

            User user = db.getUserByToken(token);
            if (user != null) {
                JsonObject resObj = new JsonObject();
                resObj.addProperty("username", user.getUsername());
                resObj.addProperty("email", user.getEmail());
                resObj.addProperty("password", user.getPassword());
                resObj.addProperty("token", user.getToken());
                System.out.println(resObj);
                out.print(resObj);
            }
            out.close();                      
            res.setStatus(HttpServletResponse.SC_OK);
        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }


    }
}

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.*;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        System.out.println("login");
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
            System.out.println(reqBody);

            JsonObject obj = new Gson().fromJson(reqBody, JsonObject.class);
            System.out.println(obj);
            // JsonObject userObj = obj.get("user").getAsJsonObject();
            // System.out.println(userObj);
            // String username = userObj.get("username").toString();
            String email = obj.get("email").toString();
            String password = obj.get("password").toString();

            if (!db.userExists(email)) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.close();
                return;
            } else {
                User user = db.getUserByEmail(email);
                System.out.println(user.getPassword());
                if (helper.comparePassword("\""+user.getPassword()+"\"", password)) {
                    String token = helper.generateNewToken();                    
                    JsonObject resObj = new JsonObject();
                    resObj.addProperty("token", token);
                    resObj.add("user", obj);
                    System.out.println(resObj);
                    out.print(resObj);
                    out.close();                      
                    res.setStatus(HttpServletResponse.SC_OK);
                } else {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.close();
                    return;
                }
            }

        }catch(Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }


    }
}

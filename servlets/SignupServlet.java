import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.*;
// import javax.servlet.http.Cookie;
// import com.okta.createverifytokens;

@WebServlet("/user/signup")
public class SignupServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException {
        Helper helper = new Helper();
        DbConnection db = new DbConnection();
        // JWTDemo jwt = new JWTDemo();
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
            JsonObject userObj = obj.get("user").getAsJsonObject();
            System.out.println(userObj);
            String username = userObj.get("username").toString();
            String email = userObj.get("email").toString();
            String password = userObj.get("password").toString();

            if (db.userExists(email)) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.close();
                return;
            } else {
                String token = helper.generateNewToken();
                System.out.println(token);
                User user = new User(username, password, email, token);
                db.addUser(user);

                userObj.addProperty("token", token);
                JsonObject resObj = new JsonObject();
                // resObj.addProperty("token", token);
                resObj.add("user", userObj);
                System.out.println(resObj);

                // Cookie cookie = new Cookie("authToken", token);
                // res.addCookie(cookie);
                // helper.addHeaderCookie(res, "authToken", token);

                out.print(resObj);
                out.close();                      
                res.setStatus(HttpServletResponse.SC_OK);
            }

        }catch(Exception ex) {
            System.out.println(ex.getMessage());
          ex.printStackTrace();
      }


  }
}

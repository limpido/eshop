import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.*;

@WebServlet("/shopping-cart/get")
public class ShoppingCartGetServlet extends HttpServlet {
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
            System.out.println(obj);

            int uid = obj.get("uid").getAsInt();
            JsonArray items = db.getCartItemsByUid(uid);
            out.print(items);

            out.close();                      
            res.setStatus(HttpServletResponse.SC_OK);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
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

            

            out.close();                      
            res.setStatus(HttpServletResponse.SC_OK);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}

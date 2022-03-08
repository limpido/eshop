import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.*;

@WebServlet("/db")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class QueryServlet extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
      System.out.println("get");
      Helper helper = new Helper();
      helper.addHeader(response);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      try (
         Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
         Statement stmt = conn.createStatement();
         ) {
         System.out.println("database connected");
         String authors = null;
         if (authors == null) {
            String sqlStr = "select * from books";
            ResultSet rset = stmt.executeQuery(sqlStr);
            JsonArray arr = new JsonArray();
            while(rset.next()) {
             JsonObject inner = new JsonObject();
             inner.addProperty("author", rset.getString("author"));
             inner.addProperty("title", rset.getString("title"));
             inner.addProperty("price", rset.getDouble("price"));
             arr.add(inner);
          }
          System.out.println(arr);
          out.print(arr);
            return; // Exit doGet()
         }
         
         String sqlStr = "select * from books where author IN (" + authors;
         sqlStr += ") AND qty > 0 ORDER BY author ASC, title ASC";
         System.out.println(sqlStr);
         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
         System.out.println(rset);

         int count = 0;
         JsonArray arr = new JsonArray();
         while(rset.next()) {
          JsonObject inner = new JsonObject();
          inner.addProperty("author", rset.getString("author"));
          inner.addProperty("title", rset.getString("title"));
          inner.addProperty("price", rset.getDouble("price"));
          arr.add(inner);
          count++;
       }
       System.out.println(arr);
       out.print(arr);
    } catch(Exception ex) {
      ex.printStackTrace();
   }
   out.close();
}

@Override
public void doPost (HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
   Helper helper = new Helper();
   helper.addHeader(response);
   response.setContentType("application/json");
   response.setCharacterEncoding("UTF-8");
   PrintWriter out = response.getWriter();

   try (
      Connection conn = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
      Statement stmt = conn.createStatement();
      ) {
      System.out.println("database connected");
      String reqBody = helper.getBody(request);
      System.out.println(reqBody);
      JsonObject jobj = new Gson().fromJson(reqBody, JsonObject.class);
      System.out.println(jobj);
      String authors = jobj.get("author").toString();
      System.out.println(authors);

      String sqlStr = "select * from books where author=" + authors;
      sqlStr += " AND qty > 0 ORDER BY author ASC, title ASC";
      System.out.println(sqlStr);
         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
         System.out.println(rset);
         int count = 0;
         JsonArray arr = new JsonArray();
         while(rset.next()) {
          JsonObject inner = new JsonObject();
          inner.addProperty("author", rset.getString("author"));
          inner.addProperty("title", rset.getString("title"));
          inner.addProperty("price", rset.getDouble("price"));
          arr.add(inner);
          count++;
       }
       System.out.println(arr);
       out.print(arr);
    } catch(Exception ex) {
      ex.printStackTrace();
   }
   out.close();
}
}


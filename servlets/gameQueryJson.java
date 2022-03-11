import java.io.*;
import java.sql.*;
import jakarta.servlet.*;           
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.*;


@WebServlet("/gamequery")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class gameQueryJson extends HttpServlet {

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
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/gamelib?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {
         System.out.println("database connected");

         /*String searchtitle = request.getParameter("searchtitle");//input from user end
         
         String searchgenre = request.getParameter("searchgenre");
         
         String searchprice = request.getParameter("searchprice");*/
         
         //out.println("title:" +searchtitle+ "genre:" + searchgenre+ "price:"+searchprice);
         // Step 3: Execute a SQL SELECT query
         String sqlStr = null;

         if (title != null && price == null && genre == null){//title:yes, genre:null, value:null
            sqlStr = "select * from game where title LIKE "
               + "'%" + searchtitle + "%'";
               sqlStr += " order by qtySold asc";
         }
         else if (title != null && price != null && genre == null){
            sqlStr = "select * from game where title LIKE "
               + "'%" + searchtitle + "%' AND price " 
               +searchprice+ " order by qtySold asc";
         }
         else if (title != null && price == null && genre != null){
            sqlStr = "select * from game where title LIKE "
               + "'%" + searchtitle + "%' AND genre = '" 
               +searchgenre+ "' order by qtySold asc";
         }
         else if (title != null && price != null && genre != null){
            sqlStr = "select * from game where title LIKE "
               + "'%" + searchtitle + "%' AND genre = '" +searchgenre+ "'"  
               + " AND price " +searchprice 
               + " order by qtySold asc";
         }
         else if (title == null && price != null && genre != null){
            sqlStr = "select * from game where genre = '" +searchgenre+ "'"
               +  "AND price " +searchprice
               + " order by qtySold asc";
               
         }
         else if (title == null && price == null && genre != null){
            sqlStr = "select * from game where genre = '" +searchgenre+ "' order by qtySold asc";

         }
         else if (title == null  && price != null && genre == null){
            sqlStr = "select * from game where price " 
               +searchprice+ " order by qtySold asc";   

         }
         else{ 
            sqlStr= null;
         }
         

         //out.println("<h3>Thank you for your query.</h3>");
         //out.println("<p>Your SQL statement is: " + sqlStr + "</p>"); // Echo for debugging
         

         if(sqlStr == null){
            System.out.println("no input Detected please try again");
            /*out.println("<form method='get' action='http://localhost:9999/gameshop/gamesearch.html'>"); // try again button
            out.println("<p><input type='submit' value='try again' />");
            out.println("</form>");*/
         }
         else{
            ResultSet rset = stmt.executeQuery(sqlStr);  // Send the
         // Step 4: Process the query result s query to the server
            JsonArray arr = new JsonArray();
            while(rset.next()) {
                JsonObject inner = new JsonObject();
                inner.addProperty("gameID", rset.getString("gameID"));
                inner.addProperty("title", rset.getString("title"));
                inner.addProperty("genre", rset.getDouble("genre"));
                inner.addProperty("developer", rset.getString("developer"));
                inner.addProperty("price", rset.getDouble("price"));
                inner.addProperty("qtySold", rset.getString("qtySold"));
                inner.addProperty("image_path", rset.getString("image_path"));
                arr.add(inner);
            }
            /*out.println("<form method='get' action='gameCart'>");   

          //print order button
            while(rset.next()) {
               // Print a paragraph <p>...</p> for each record
               out.println("<p><input type='checkbox' name='gameID' value="
                     + "'" + rset.getString("gameID") + "' />" 
                     + rset.getString("title")
                     + ", " + rset.getString("genre")
                     + ", $" + rset.getString("price")+ ", QTY sold = "
                     + rset.getString("qtySold") + "</p>");
            }
            out.println("<p><input type='submit' value='Add to Cart' />");
            
            out.println("</form>");
            
            out.println("<form method='get' action='http://localhost:9999/gameshop/gamesearch.html'>");//rstart search
            out.println("<p><input type='submit' value='continue searching' />");
            out.println("</form>");*/
         }

         
      } catch(Exception ex) {
         ex.printStackTrace();
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)
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
         String title = jobj.get("title").toString();
         System.out.println(title);

         String sqlStr = "select * from games where title=" + title;
         sqlStr += " order by qtySold asc";
         System.out.println(sqlStr);
            ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
            System.out.println(rset);
            int count = 0;
            JsonArray arr = new JsonArray();
            while(rset.next()) {
             JsonObject inner = new JsonObject();
             inner.addProperty("gameID", rset.getString("gameID"));
                inner.addProperty("title", rset.getString("title"));
                inner.addProperty("genre", rset.getDouble("genre"));
                inner.addProperty("developer", rset.getString("developer"));
                inner.addProperty("price", rset.getDouble("price"));
                inner.addProperty("qtySold", rset.getString("qtySold"));
                inner.addProperty("image_path", rset.getString("image_path"));
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
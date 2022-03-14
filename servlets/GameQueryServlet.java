import java.io.*;
import java.sql.*;
import jakarta.servlet.*;           
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.*;


@WebServlet("/game")
public class GameQueryServlet extends HttpServlet {

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
      Helper helper = new Helper();
      helper.addHeader(response);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();

      try (
         Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/gamelib?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
            "myuser", "xxxx");
         Statement stmt = conn.createStatement();
         ) {
         System.out.println("game query");
         String title = request.getParameter("title");
         String price = request.getParameter("price");
         String genre = request.getParameter("genre");
         System.out.println(title);
         System.out.println(price);
         System.out.println(genre);

         if (title == "")
            title = null;

         String sqlStr = null;

         if (title != null && price == null && genre == null){//title:yes, genre:null, value:null
            sqlStr = "select * from game where title LIKE "
            + "'%" + title + "%'";
            sqlStr += " order by qtySold desc";
         }
         else if (title != null && price != null && genre == null){
            sqlStr = "select * from game where title LIKE "
            + "'%" + title + "%' AND price " 
            +price+ " order by qtySold desc";
         }
         else if (title != null && price == null && genre != null){
            sqlStr = "select * from game where title LIKE "
            + "'%" + title + "%' AND genre = '" 
            +genre+ "' order by qtySold desc";
         }
         else if (title != null && price != null && genre != null){
            sqlStr = "select * from game where title LIKE "
            + "'%" + title + "%' AND genre = '" +genre+ "'"  
            + " AND price " +price 
            + " order by qtySold desc";
         }
         else if (title == null && price != null && genre != null){
            sqlStr = "select * from game where genre = '" +genre+ "'"
            +  "AND price " +price
            + " order by qtySold desc";

         }
         else if (title == null && price == null && genre != null){
            sqlStr = "select * from game where genre = '" +genre+ "' order by qtySold desc";
         }
         else if (title == null  && price != null && genre == null){
            sqlStr = "select * from game where price " 
            +price+ " order by qtySold desc";   
         }
         

         if(sqlStr == null){
            System.out.println("no input Detected please try again");
         }
         else{
            System.out.println(sqlStr);
            ResultSet rset = stmt.executeQuery(sqlStr);
            JsonArray arr = new JsonArray();
            while(rset.next()) {
             JsonObject inner = new JsonObject();
             inner.addProperty("gameId", rset.getString("gameId"));
             inner.addProperty("title", rset.getString("title"));
             inner.addProperty("genre", rset.getString("genre"));
             inner.addProperty("developer", rset.getString("developer"));
             inner.addProperty("price", rset.getDouble("price"));
             inner.addProperty("qtySold", rset.getString("qtySold"));
             inner.addProperty("image_path", rset.getString("image_path"));
             arr.add(inner);
          }
          out.print(arr);
          response.setStatus(HttpServletResponse.SC_OK);
       }
    } catch(Exception ex) {
      ex.printStackTrace();
      System.out.println(ex.getMessage());
   }
   out.close();
}
}
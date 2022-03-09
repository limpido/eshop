import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import com.google.gson.*;
import org.apache.commons.text.StringSubstitutor;
import java.util.*;


public class DbConnection {
	private Connection conn;
	private Statement stmt;

	public Connection connect() throws SQLException {
		try {
			this.conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/gamelib?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
				"myuser", "xxxx");
			this.stmt = conn.createStatement();
			return this.conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public User getUserByEmail(String email) throws SQLException {
		String query = "SELECT * FROM user WHERE email="+email+";";
		ResultSet rset = this.stmt.executeQuery(query);
		rset.next();
		String username = rset.getString("username");
		// String email = rset.getString("email");
		String password = rset.getString("password");
		String token = rset.getString("token");
		User user = new User(username, password, email, token);
		return user;
	}

	public User getUserByToken(String token) throws SQLException {
		String query = "SELECT * FROM user WHERE token="+token+";";
		System.out.println(query);
		ResultSet rset = this.stmt.executeQuery(query);
		rset.next();
		if (rset.getString("username") != null) {
			String username = rset.getString("username");
			String email = rset.getString("email");
			String password = rset.getString("password");
			User user = new User(username, password, email, token);
			return user;
		} else return null;
	}

	public boolean userExists(String email) throws SQLException {
		System.out.println("userexists?");
		String query = "SELECT COUNT(*) AS usercount FROM user WHERE email="+email+";";
		System.out.println(query);
		ResultSet rset = this.stmt.executeQuery(query);
		rset.next();
		System.out.println(rset.getInt("usercount"));
		return rset.getInt("usercount") > 0 ? true : false;
	}

	public void addUser(User user) throws SQLException {
		try {
			Map<String, String> values = new HashMap<>();
			values.put("username", user.getUsername());
			values.put("password", user.getPassword());
			values.put("email", user.getEmail());
			values.put("token", user.getToken());
			StringSubstitutor sub = new StringSubstitutor(values);
			String query = sub.replace("INSERT INTO user (username, email, password, token) VALUES (${username}, ${email}, ${password}, \"${token}\");");
			System.out.println(query);
			this.stmt.executeUpdate(query);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateUserToken(User user, String token) throws SQLException {
		try {
			Map<String, String> values = new HashMap<>();
			values.put("email", user.getEmail());
			values.put("token", token);
			StringSubstitutor sub = new StringSubstitutor(values);
			String query = sub.replace("UPDATE user SET token=\"${token}\" WHERE email=${email}");
			System.out.println(query);
			this.stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public JsonArray getGames() throws SQLException {
		try {
			String query = "SELECT * FROM game;";
			ResultSet rset = this.stmt.executeQuery(query);
			JsonArray games = new JsonArray();
			while (rset.next()) {
				JsonObject gameobj = new JsonObject();
				gameobj.addProperty("title", rset.getString("title"));
				gameobj.addProperty("developer", rset.getString("developer"));
				gameobj.addProperty("price", rset.getDouble("price"));
				gameobj.addProperty("qtySold", rset.getInt("qtySold"));
				games.add(gameobj);
			}
			return games;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
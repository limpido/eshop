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
		User user = new User(username, password, email);
		return user;
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
		System.out.println("addUser");
		Map<String, String> values = new HashMap<>();
		values.put("username", user.getUsername());
		values.put("password", user.getPassword());
		values.put("email", user.getEmail());
		StringSubstitutor sub = new StringSubstitutor(values);
		String query = sub.replace("INSERT INTO user (username, email, password) VALUES (${username}, ${email}, ${password});");
		this.stmt.executeUpdate(query);			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
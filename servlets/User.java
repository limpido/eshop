public class User {
	private String username;
	private String password;
	private String email;
    private String token;

	public User(String username, String password, String email, String token) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.token = token;
    }


    public String getUsername() {
    	return username;
    }

    public String getPassword() {
    	return password;
    }

    public String getEmail() {
    	return email;
    }

    public String getToken() {
        return token;
    }
}
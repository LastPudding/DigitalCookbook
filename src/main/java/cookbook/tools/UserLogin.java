package cookbook.tools;

import java.sql.*;

import cookbook.model.Recipe;
import cookbook.model.User;

public class UserLogin extends User {
	UserLogin(String username, String password) {
		super(username, password);
	}

	boolean isUserAuthenticated() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", super.userName, super.password);
			con.close();
		} catch (SQLException e) {
			return false;
		}
		return true;

	}

	public static void main(String args[]) throws ClassNotFoundException {
		UserLogin test1 = new UserLogin("root", "1q2w3e4r");
		UserLogin test2 = new UserLogin("roote", "1q2w3e4r");
		System.out.println(test1.isUserAuthenticated());
		System.out.println(test2.isUserAuthenticated());
	}

}

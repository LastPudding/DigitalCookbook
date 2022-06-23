package cookbook.tools;

import java.sql.*;
import java.util.LinkedList;

import cookbook.model.Category;
import cookbook.model.Recipe;

public class CategoryDAO {

	static LinkedList<Category> getAllCategory() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");

		String sql = "select * from category";

		Statement stat = con.createStatement();

		ResultSet count = stat.executeQuery(sql);
		LinkedList<Category> result = new LinkedList<>();
		if (count.isLast()) {
			System.out.print("No Category!");
			return null;
		}
		do {
			count.next();
			int categoryID = Integer.parseInt(count.getString(1));
			String CategoryName = count.getString(2);
			result.add(new Category(categoryID, CategoryName));
		} while (!count.isLast());

		stat.close();
		con.close();
		return result;
	}

	public static LinkedList<String> getAllCategoryString() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");

		String sql = "select * from category";

		Statement stat = con.createStatement();

		ResultSet count = stat.executeQuery(sql);
		LinkedList<String> result = new LinkedList<>();
		if (count.isLast()) {
			System.out.print("No Category!");
			return null;
		}
		do {
			count.next();
			String CategoryName = count.getString(2);
			result.add(CategoryName);
		} while (!count.isLast());

		stat.close();
		con.close();
		return result;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		LinkedList<Category> test2 = CategoryDAO.getAllCategory();
		for (Category temp : test2) {
			System.out.print(temp.name);
		}

		// addNewCategory("diary");
	}

	static void addNewCategory(String name) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");
		String sql = "insert into Category (name) values ('" + name + "');";
		Statement stat = con.createStatement();
		stat.executeUpdate(sql);
		stat.close();
		con.close();
	}

}

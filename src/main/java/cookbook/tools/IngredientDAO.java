package cookbook.tools;

import java.sql.*;
import java.util.LinkedList;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;

public class IngredientDAO {

	static LinkedList<Ingredient> getIngredientByCategory(String category) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");

		String sql = "select * from ingredient,category,recipe_use_ingredient where recipe_use_ingredient.ingredient_id=ingredient.id and ingredient.category_id =category.id and category.name ='"
				+ category + "'";

		Statement stat = con.createStatement();

		ResultSet count = stat.executeQuery(sql);
		if (count.isLast()) {
			System.out.print("Wrong Category!");
			return null;
		}
		LinkedList<Ingredient> result = new LinkedList<>();
		try {
			do {
				count.next();
				String ingredientName = count.getString(1);
				int ingredientCategoryID = Integer.parseInt(count.getString(2));
				String ingredientCategory = count.getString(3);
				int ingredientAmount = -Integer.parseInt(count.getString(4));
				String ingredientUnit = count.getString(5);
				result.add(new Ingredient(ingredientName, ingredientCategoryID, ingredientCategory, ingredientAmount,
						ingredientUnit));
			} while (!count.isLast());
		} catch (Exception e) {
			System.out.println("No Ingredient Found by Provided Category!");
			// TODO: handle exception
		}

		stat.close();
		con.close();
		return result;
	}

	static Ingredient getIngredientByName(String ingredientName) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");
		String sql = "select ingredient.id,ingredient.`name`,category_id,category.`name` from ingredient,category where ingredient.category_id = category.id AND ingredient.name = '"
				+ ingredientName + "';";
		Statement stat = con.createStatement();
		ResultSet count = stat.executeQuery(sql);
		count.next();
		try {
			Ingredient result = new Ingredient(count.getString(2), Integer.parseInt(count.getString(3)),
					count.getString(4), 0, "");
			result.setId(Integer.parseInt(count.getString(1)));
			return result;

		} catch (SQLException e) {
			stat.close();
			con.close();
			return null;
			// TODO: handle exception
		}

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		LinkedList<Ingredient> test2 = IngredientDAO.getIngredientByCategory("Seasoning");
		Recipe test1 = RecipeDAO.getRecipeByName("Tomato with Fried Egg");
		// addNewIngredient("milk", "diary");
		System.out.println(isIngredientExist("Tomato"));
		System.out.println(isIngredientExist("Potato"));
		System.out.println(isIngredientExist("DoNotExist"));
	}

	public static boolean isIngredientExist(String ingredientName) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");
		String sql = "select * from ingredient where name = '" + ingredientName + "';";
		Statement stat = con.createStatement();
		ResultSet count = stat.executeQuery(sql);
		count.next();
		try {
			count.getString(1);
			return true;
		} catch (SQLException e) {
			stat.close();
			con.close();
			return false;
			// TODO: handle exception
		}

	}

	public static void deleteIngredient(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");
		String sql = "delete from ingredient where id = '" + id + "';";
		Statement stat = con.createStatement();
		stat.executeUpdate(sql);
		stat.close();
		con.close();
	}

	public static void deleteIngredientByRecipeID(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");
		String sql = "delete from recipe_use_ingredient where recipe_id = '" + id + "';";
		Statement stat = con.createStatement();
		stat.executeUpdate(sql);
		stat.close();
		con.close();
	}

	public static void addNewIngredient(Ingredient ingredient) throws ClassNotFoundException, SQLException {
		if (!isIngredientExist(ingredient.getName())) {
			addNewIngredient(ingredient.getName(), ingredient.getCategory());
		}

	}

	public static void addNewIngredient(String name, String category) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");

		String sql = "select * from category where name ='" + category + "'";

		Statement stat = con.createStatement();

		ResultSet count = stat.executeQuery(sql);
		if (count.isLast()) {
			System.out.print("Wrong Category!");
			return;
		}
		count.next();

		sql = "insert into ingredient (name,category_id) values ('" + name + "','" + count.getString(1) + "');";
		stat = con.createStatement();
		stat.executeUpdate(sql);
		stat.close();
		con.close();
	}

}

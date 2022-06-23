package cookbook.tools;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;

public class RecipeDAO {

	public static Recipe getRecipeByName(String name) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");

		String sql = "select * from recipe where recipe.name ='" + name + "'";

		Statement stat = con.createStatement();

		ResultSet count = stat.executeQuery(sql);
		if (count.isLast()) {
			System.out.print("Wrong Name!");

			stat.close();
			con.close();
			return null;
		}
		count.next();
		Recipe temp;
		try {
			String temp1 = count.getString(1);
			int recipeID = Integer.parseInt(count.getString(1));
			String recipeName = count.getString(2);
			int recipeServesPeople = Integer.parseInt(count.getString(3));
			int cookTime = Integer.parseInt(count.getString(4));
			// int timeUnit = Integer.parseInt(count.getString(5));
			String steps = count.getString(6);
			temp = new Recipe(recipeID, recipeName, recipeServesPeople, cookTime, steps);
			sql = "select ingredient.name,ingredient.category_id,category.`name`,amount,unit from ingredient,category,recipe_use_ingredient where recipe_use_ingredient.ingredient_id=ingredient.id and ingredient.category_id =category.id and recipe_id ='"
					+ recipeID + "'";
		} catch (SQLException e) {
			System.out.println("No Recipe Found by Provided Name!");
			return null;
		}

		stat = con.createStatement();
		count = stat.executeQuery(sql);

		try {
			do {
				count.next();

				String ingredientName = count.getString(1);
				int IngredientCategoryID = Integer.parseInt(count.getString(2));
				String IngredientCategory = count.getString(3);
				int ingredientAmount = Integer.parseInt(count.getString(4));
				String IngredientUnit = count.getString(5);
				temp.addIngredient(new Ingredient(ingredientName, IngredientCategoryID, IngredientCategory,
						ingredientAmount, IngredientUnit));
			} while (!count.isLast());

		} catch (SQLException e) {
			System.out.println("No Ingredient Attached Recipe Inquir");
			return temp;
		}

		return temp;
	}

	public static LinkedList<Recipe> getRecipesByUserID(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");

		String sql = "select * from recipe where recipe.user_id ='" + id + "'";

		Statement stat = con.createStatement();

		ResultSet count = stat.executeQuery(sql);
		if (count.isLast()) {
			System.out.print("Wrong UserID!");
			return null;
		}
		LinkedList<Recipe> result = new LinkedList<>();
		;
		do {
			count.next();
			String recipeName = count.getString(2);
			int recipeServesPeople = Integer.parseInt(count.getString(3));
			result.add(new Recipe(recipeName, recipeServesPeople));
		} while (!count.isLast());

		stat.close();
		con.close();
		return result;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Recipe test1 = RecipeDAO.getRecipeByName("TFE");
		// LinkedList<Recipe> test2 = RecipeDAO.getRecipesByUserID(1);
		Ingredient test2 = new Ingredient("potato", 1, "Vegetable", 3, "piece");
		IngredientDAO.addNewIngredient(test2);
		test1.addIngredient(test2);
		updateRecipe(test1);
	}

	public static void updateRecipe(Recipe recipeToUpdate) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");
		String sql = "UPDATE recipe SET name='" + recipeToUpdate.getName() + "'," +
				" serves='" + recipeToUpdate.getServePeople() + "'," +
				"cook_time='" + recipeToUpdate.getCookTime() + "'," +
				"steps='" + recipeToUpdate.getSteps() + "'" +
				"WHERE id=" + recipeToUpdate.getID() + ";";
		Statement stat = con.createStatement();
		stat.executeUpdate(sql);
		stat.close();
		con.close();
		updateIngredientOfRecipe(recipeToUpdate);
	}

	public static void deleteRecipeByID(int recipeID) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");
		String sql = "delete from recipe where id='" + recipeID + "';";
		Statement stat = con.createStatement();
		stat.executeUpdate(sql);

		stat.close();
		con.close();
	}

	public static void addNewRecipe(Recipe recipeToAdd) throws ClassNotFoundException, SQLException {
		addNewRecipe(recipeToAdd.getName(), recipeToAdd.getServePeople(), recipeToAdd.getCookTime(),
				recipeToAdd.getSteps());
		recipeToAdd = RecipeDAO.getRecipeByName(recipeToAdd.getName());
		updateIngredientOfRecipe(recipeToAdd);

	}

	private static void updateIngredientOfRecipe(Recipe recipeToAdd) throws SQLException, ClassNotFoundException {
		IngredientDAO.deleteIngredientByRecipeID(recipeToAdd.getID());
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");
		for (Ingredient temp : recipeToAdd.getIngredientList()) {
			if (!IngredientDAO.isIngredientExist(temp.getName())) {
				IngredientDAO.addNewIngredient(temp);

			}
			temp.setId(IngredientDAO.getIngredientByName(temp.getName()).getId());
			IngredientDAO.getIngredientByName(temp.getName()).getId();
			String sql = "insert into recipe_use_ingredient (recipe_id,ingredient_id,amount,unit) values ('"
					+ recipeToAdd.getID() + "','"
					+ temp.getId() + "', '"
					+ temp.getAmount()
					+ "','" + temp.getUnit() + "');";
			Statement stat = con.createStatement();
			stat.executeUpdate(sql);
			stat.close();
		}

		con.close();
	}

	public static void addNewRecipe(String name, int servePeople, int cookTime, String steps)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");
		String sql = "insert into recipe (name,serves,cook_time,steps) values ('" + name + "','" + servePeople + "', '"
				+ cookTime
				+ "','" + steps + "');";
		Statement stat = con.createStatement();
		stat.executeUpdate(sql);
		stat.close();
		con.close();

	}

}

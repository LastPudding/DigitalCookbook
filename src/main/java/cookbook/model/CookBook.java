package cookbook.model;

import java.util.ArrayList;
import java.util.List;

import cookbook.tools.IngredientDAO;
import cookbook.tools.RecipeDAO;

public class CookBook {
	public static Recipe nowRecipe;
	public static List<Ingredient> ingredients = new ArrayList<Ingredient>();
	public static List<Recipe> recipeList = new ArrayList<Recipe>();

	public void update() {
	}

	/**
	 * Controller of main model class, initialize the local data according to the
	 * Database
	 */
	// public CookBook() {
	//
	// try{
	// recipeList = RecipeDAO.getAllRecipes();
	// }catch(Exception e) {
	//// System.out.println(e);
	// }
	// for(Recipe i:recipeList) {
	//// System.out.println(i.getRecipeName());
	// try{
	// i.setIngredientList(IngredientDAO.getIngredientsByRecipeId(i.getId()));
	// }catch(Exception e) {
	//// System.out.println(e);
	// }
	// }
	//
	// }

	/**
	 * Updates the local data according to the Database when invoked
	 */
	// public static void update() {
	// try{
	// recipeList = RecipeDAO.getAllRecipes();
	// }catch(Exception e) {
	//// System.out.println(e);
	// }
	// for(Recipe i:recipeList) {
	//// System.out.println(i.getRecipeName());
	// try{
	// i.setIngredientList(IngredientDAO.getIngredientsByRecipeId(i.getId()));
	// }catch(Exception e) {
	//// System.out.println(e);
	// }
	// }
	// }

}

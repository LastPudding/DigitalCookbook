package cookbook.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Recipe {
	private int id;
	private String name;
	private int servePeople;// number of people served

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int cookTime;

	private String imageURL;
	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private String steps;

	public Recipe() {
	}

	public Recipe(String title, int servePeople) {
		this.name = title;
		this.servePeople = servePeople;
	}

	public Recipe(String recipeName, int servePeople, int cookTime, String steps) {
		this.name = recipeName;
		this.servePeople = servePeople;
		this.cookTime = cookTime;
		this.steps = steps;
	}

	public Recipe(int recipeID, String recipeName, int servePeople, int cookTime, String steps) {
		this.id = recipeID;
		this.name = recipeName;
		this.servePeople = servePeople;
		this.cookTime = cookTime;
		this.steps = steps;
	}

	/**
	 * add a single steps to the recipe
	 * 
	 * @param aStep a single step added to the recipe
	 * @return boolean whether the steps were carried out successfully
	 */
	// public boolean addSteps(String aStep) {
	// return steps.add(aStep);
	// }

	/**
	 * add a list of steps to the recipe
	 * 
	 * @param stepList a list of steps added to the recipe
	 * @return boolean hether the steps were carried out successfully
	 */
	// public boolean addSteps(LinkedList<String> stepList) {
	// boolean result = true;
	// for (String item : stepList) {
	// result = result && steps.add(item);
	// }
	// return result;
	// }
	//
	// public LinkedList<String> getSteps() {
	// return steps;
	// }
	//
	// public void setStep(int stepNum, String newStep) {
	// steps.add(stepNum - 1, newStep);
	// steps.remove(stepNum);
	// }
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getServePeople() {
		return servePeople;
	};

	public void setServePeople(int servePeople) {
		this.servePeople = servePeople;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(ArrayList<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

	public String getImagePath() {
		return imageURL;
	}

	public void setImagePath(String imagePath) {
		this.imageURL = imagePath;
	}

	/**
	 * returns the total number of steps in the menu
	 * 
	 * @return int number total number of steps
	 */
	// public int getStepCount() {
	// return steps.size();
	// }

	public void addIngredient(Ingredient... ingredients) {
		for (Ingredient i : ingredients) {
			ingredientList.add(i);
		}
	}

	public void clearIngredient() {
		this.ingredientList.clear();
	}

//	public String showRecipe() {
//		String result = "";
//		for (Ingredient i : ingredientList)
//			result = result + "  ---- " + i.toString() + ";\n";
//		return ("\nName:                   " + this.name + "\n\n" + "Serve People:                   " + servePeople
//				+ "\n\n" + "Cook time:                   " + cookTime
//				+ "\n\n" + "Ingredients: \n" + result + "\n\n" + "Steps: \n" + steps);
//	}
//	
//	public String showRecipeXPeople(int x) {
//		String result = "";
//		for (Ingredient i : ingredientList) {
//			result = result + "  ---- " + i.toStringXPeople(x, servePeople) + ";\n";
//		}
//		return ("\nName:                   " + this.name + "\n\n" + "Serve People:                   " + servePeople
//				+ "\n\n" + "Cook time:                   " + cookTime
//				+ "\n\n" + "Ingredients: \n" + result + "\n\n" + "Steps: \n" + steps);
//	}

	public String showSteps() {
		return steps;
	}

	public String showIngredients() {
		String result = "";
		for (Ingredient temp : ingredientList) {
			result += temp.getName() + ": " + temp.getAmount() + " " + temp.getUnit() + ";\n";
		}
		result = result.substring(0, result.lastIndexOf(";"));
		result += ".";
		return result;
	}
	
	public String showIngredientsXPeople(int x) {
		String result = "";
		for (Ingredient temp : ingredientList) {
			int quantity = temp.getAmount() / servePeople * x;
			result += temp.getName() + ": " + quantity + " " + temp.getUnit() + ";\n";
		}
		result = result.substring(0, result.lastIndexOf(";"));
		result += ".";
		return result;
	}

}

package cookbook.model;

import java.util.ArrayList;

public class Ingredient {
	private int id;
	private String name = "";
	private int categoryID;

	private String category;
	private int amount;
	private String unit = "";
	// private static ArrayList<Ingredient> ingredientList = new ArrayList<>();

	public Ingredient() {
		// TODO Auto-generated constructor stub
	}

	public Ingredient(String name, String category) {
		this.name = name;
		this.category = category;
		// ingredientList.add(this);
	}

	public Ingredient(String name, int id) {
		this.name = name;
		this.id = id;
		// ingredientList.add(this);
	}

	public Ingredient(String name, String category, int amount, String unit) {
		this.name = name;
		this.category = category;
		this.amount = amount;
		this.unit = unit;
		// ingredientList.add(this);
	}

	public Ingredient(String name, int categoryID, String category, int amount, String unit) {
		this.name = name;
		this.categoryID = categoryID;
		this.category = category;
		this.amount = amount;
		this.unit = unit;
		// ingredientList.add(this);
	}

	/**
	 * @return the categoryID
	 */
	public int getCategoryID() {
		return categoryID;
	}

	/**
	 * @param categoryID the categoryID to set
	 */
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String toString() {
		String result;
		result = "  " + amount + "  " + unit + "  " + name;
		if (!(this.category == null))
			result += ",  < " + category + " >";
		return result;
	}

	public String toStringXPeople(int x, int servePeople) {
		String result;
		result = (amount / servePeople) * x + " " + unit + " " + name;
		return result;
	}

}

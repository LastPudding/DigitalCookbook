package cookbook.model;

public class Category {
	public String name;
	public int id;

	public Category(String name) {
		this.name = name;
	}

	public Category(int categoryID, String categoryName) {
		this.id = categoryID;
		this.name = categoryName;
	}
}

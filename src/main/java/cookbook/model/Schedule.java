package cookbook.model;

public class Schedule {
    String date;
    int recipeID;
    String timeToEat;
    String recipeName;

    public Schedule(int recipeID, String timeToEat, String recipeName) {
        this.recipeID = recipeID;
        this.timeToEat = timeToEat;
        this.recipeName = recipeName;
    }
}

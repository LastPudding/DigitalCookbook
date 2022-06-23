package cookbook.tools;

import java.sql.*;
import java.util.LinkedList;

import cookbook.model.Schedule;

public class ScheduleDAO {
    public static LinkedList<Schedule> getScheduleByUserID(int userID) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://192.168.50.99:3306/recipe", "root", "1q2w3e4r");

        String sql = "select recipe_id, name, time_to_eat from schedule,recipe where user_id ='" + userID
                + "' and schedule.recipe_id=recipe.id";

        Statement stat = con.createStatement();

        ResultSet count = stat.executeQuery(sql);
        if (count.isLast()) {
            System.out.print("Wrong UserID!");
            return null;
        }
        LinkedList<Schedule> result = new LinkedList<>();
        ;
        do {
            count.next();
            int recipeID = Integer.parseInt(count.getString(1));
            String recipeName = count.getString(2);
            String timeToEat = count.getString(3);
            result.add(new Schedule(recipeID, timeToEat, recipeName));
        } while (!count.isLast());

        stat.close();
        con.close();
        return result;
    }

}

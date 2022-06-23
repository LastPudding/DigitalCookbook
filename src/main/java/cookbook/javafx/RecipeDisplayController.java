package cookbook.javafx;

import java.io.IOException;
import java.util.Optional;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import cookbook.model.CookBook;
import cookbook.tools.RecipeDAO;

public class RecipeDisplayController {

	@FXML
	private Text recipeName;
	@FXML
	private Button closeButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button reminderButton;
	@FXML
	private TextArea ingredientsTextField;
	@FXML
	private TextArea stepsTextField;
	@FXML
	private TextField takesTimeTextField;
	@FXML
	private Slider servePeopleSlider;
	@FXML
	private Label servePeopleLabel;
	// @FXML
	// private Image recipeImage;

	@FXML
	public void onCloseEvent(ActionEvent event) {
		AnchorPane searchView = null;
		BorderPane menuView = null;
		try {

			menuView = FXMLLoader.load(getClass().getResource("/cookbook.javafx/MenuView.fxml"));
			searchView = FXMLLoader.load(getClass().getResource("/cookbook.javafx/SearchView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeButton.getScene().setRoot(menuView);
		menuView.setCenter(searchView);
	}

	@FXML
	public void editEvent() {
		AnchorPane editView = null;
		BorderPane rootLayout = null;
		try {
			rootLayout = FXMLLoader.load(getClass().getResource("/cookbook.javafx/MenuView.fxml"));
			editView = FXMLLoader.load(getClass().getResource("/cookbook.javafx/EditView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editButton.getScene().setRoot(rootLayout);
		rootLayout.setCenter(editView);
	}

	@FXML
	public void deleteEvent(ActionEvent event) {

		Alert alert = new Alert(AlertType.WARNING, "Are you sure to delete this recipe?", ButtonType.YES,
				ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.YES) { // User authorizes the action.

			try {
				RecipeDAO.deleteRecipeByID(CookBook.nowRecipe.getID());
				// IngredientDAO.deleteIngredientByRecipeID(CookBook.nowRecipe.getId());
			} catch (Exception e) {

			}
			CookBook.recipeList.remove(CookBook.nowRecipe);

			AnchorPane scene1 = null;
			BorderPane rootLayout = null;
			try {
				rootLayout = FXMLLoader.load(getClass().getResource("/cookbook.javafx/MenuView.fxml"));
				scene1 = FXMLLoader.load(getClass().getResource("/cookbook.javafx/SearchView.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			deleteButton.getScene().setRoot(rootLayout);
			rootLayout.setCenter(scene1);
		}
	}

	@FXML
	public void reminderEvent(ActionEvent event) {

	}

	public void initialize() {
		recipeName.setText(CookBook.nowRecipe.getName());
		stepsTextField.setText(CookBook.nowRecipe.showSteps());
		ingredientsTextField.setText(CookBook.nowRecipe.showIngredients());
		servePeopleSlider.setValue(CookBook.nowRecipe.getServePeople());
		servePeopleLabel.setText("" + CookBook.nowRecipe.getServePeople());
		servePeopleSlider.valueProperty()
				.addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
					servePeopleLabel.setText("" + new_val.intValue());
					stepsTextField.setText(CookBook.nowRecipe.showIngredientsXPeople(new_val.intValue()));
				});
	}

}

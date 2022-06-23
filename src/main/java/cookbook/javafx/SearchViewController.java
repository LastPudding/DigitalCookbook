package cookbook.javafx;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import cookbook.model.CookBook;
import cookbook.model.Recipe;
import cookbook.tools.RecipeDAO;

public class SearchViewController {
	@FXML
	private TextField searchText;
	@FXML
	private Button searchButton;
	@FXML
	private Button backButton;
	@FXML
	private Button createRecipeButton;
	@FXML
	private Button hsrButton;
	@FXML
	private Button mpdfButton;
	@FXML
	private Button gbjdButton;

	@FXML
	public void backButtonEvent(ActionEvent event) {
		AnchorPane scene1 = null;
		BorderPane menuView1 = null;
		try {
			menuView1 = FXMLLoader.load(getClass().getResource("/cookbook.javafx/MenuView.fxml"));
			scene1 = FXMLLoader.load(getClass().getResource("/cookbook.javafx/WelcomeView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchButton.getScene().setRoot(menuView1);
		menuView1.setCenter(scene1);
	}

	@FXML
	public void createEvent() {
		AnchorPane scene2 = null;
		BorderPane menuView2 = null;
		try {
			menuView2 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/MenuView.fxml"));
			scene2 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/CreateView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createRecipeButton.getScene().setRoot(menuView2);
		menuView2.setCenter(scene2);
	}

	@FXML
	public void searchEvent() throws IOException {
		String searchName = searchText.getText();
		ObservableList<Recipe> listData = FXCollections.observableArrayList();
		try {
			if (RecipeDAO.getRecipeByName(searchName) != null) {
				CookBook.nowRecipe = RecipeDAO.getRecipeByName(searchName);
			}
		} catch (Exception e) {

		}

		AnchorPane scene3 = null;
		BorderPane menuView3 = null;

		menuView3 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/MenuView.fxml"));
		scene3 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/RecipeDisplayView.fxml"));

		backButton.getScene().setRoot(menuView3);
		menuView3.setCenter(scene3);

	}

	@FXML
	public void hsrEvent() {
		try {
			if (RecipeDAO.getRecipeByName("Hong Shao Rou") != null) {
				CookBook.nowRecipe = RecipeDAO.getRecipeByName("Hong Shao Rou");
			}
		} catch (Exception e) {

		}
		AnchorPane scene3 = null;
		BorderPane menuView3 = null;
		try {
			menuView3 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/MenuView.fxml"));
			scene3 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/RecipeDisplayView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backButton.getScene().setRoot(menuView3);
		menuView3.setCenter(scene3);
	}

	@FXML
	public void mpdfEvent() {
		try {
			if (RecipeDAO.getRecipeByName("Ma Po Dou Fu") != null) {
				CookBook.nowRecipe = RecipeDAO.getRecipeByName("Ma Po Dou Fu");
			}
		} catch (Exception e) {

		}
		AnchorPane scene3 = null;
		BorderPane menuView3 = null;
		try {
			menuView3 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/MenuView.fxml"));
			scene3 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/RecipeDisplayView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backButton.getScene().setRoot(menuView3);
		menuView3.setCenter(scene3);
	}

	@FXML
	public void gbjdEvent() {
		try {
			if (RecipeDAO.getRecipeByName("Gong Bao Ji Ding") != null) {
				CookBook.nowRecipe = RecipeDAO.getRecipeByName("Gong Bao Ji Ding");
			}
		} catch (Exception e) {

		}
		AnchorPane scene3 = null;
		BorderPane menuView3 = null;
		try {
			menuView3 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/MenuView.fxml"));
			scene3 = FXMLLoader.load(getClass().getResource("/cookbook/javafx/RecipeDisplayView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backButton.getScene().setRoot(menuView3);
		menuView3.setCenter(scene3);
	}

}

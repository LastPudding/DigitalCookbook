package cookbook.javafx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ShowViewController {
	@FXML
	private Button backButton;
	
	@FXML
	public void backButtonEvent(ActionEvent event) {
		AnchorPane searchView = null;
		BorderPane menuView = null;
		try {
			menuView = FXMLLoader.load(getClass().getResource("/cookbook.javafx/MenuView.fxml"));
			searchView = FXMLLoader.load(getClass().getResource("/cookbook.javafx/SearchView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backButton.getScene().setRoot(menuView);
		menuView.setCenter(searchView);
	}
	
	@FXML
	public void initialize() {
		
	}
}

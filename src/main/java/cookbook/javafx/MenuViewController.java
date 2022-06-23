package cookbook.javafx;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MenuViewController {
	@FXML
	private MenuItem closeItem;
	@FXML
	private MenuItem homePageItem;
	@FXML
	private MenuBar menuBar;
	
	@FXML
	public void closeEvent(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	public void homePageEvent(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION,"Do you want to go back to the home page?",ButtonType.YES, ButtonType.CANCEL);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.YES) {
			AnchorPane welcomeView = null;
			BorderPane menuView = null;
			try {
				menuView = FXMLLoader.load(getClass().getResource("/cookbook.javafx/MenuView.fxml"));
				welcomeView = FXMLLoader.load(getClass().getResource("/cookbook.javafx/WelcomeView.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menuBar.getScene().setRoot(menuView);
			menuView.setCenter(welcomeView);
		}
	}


    public void aboutEvent(ActionEvent actionEvent) {
    }
}

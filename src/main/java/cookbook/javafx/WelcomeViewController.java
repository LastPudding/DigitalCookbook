package cookbook.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class WelcomeViewController {
	@FXML
	private Button startButton;
	
	public void startButtonEvent() {
		AnchorPane scene = null;
		BorderPane rootLayout = null;
		try {
			rootLayout = FXMLLoader.load(getClass().getResource("/cookbook/javafx/MenuView.fxml"));
			scene = FXMLLoader.load(getClass().getResource("/cookbook/javafx/SearchView.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startButton.getScene().setRoot(rootLayout);
		rootLayout.setCenter(scene);
	}

}

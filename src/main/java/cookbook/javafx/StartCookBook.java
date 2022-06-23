package cookbook.javafx;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class StartCookBook extends Application {
	public static void main(String[] args) {

		Application.launch();

	}

//	private CookBook cookbook = new CookBook();
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Recipe Box");
		initRootLayout();
		showFrameView();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {


			FXMLLoader loader = new FXMLLoader(StartCookBook.class.getResource("MenuView.fxml"));
			URL temp =StartCookBook.class.getResource("MenuView.fxml");
			loader.setLocation(StartCookBook.class.getResource("MenuView.fxml"));
			rootLayout = loader.load();
			Scene scene = new Scene(rootLayout,1000,700);

			// Show the scene containing the root layout.
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	/**
	 * Shows the frame overview inside the root layout.
	 */
	private void showFrameView() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(StartCookBook.class.getResource("WelcomeView.fxml"));
			AnchorPane frameView = loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(frameView);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

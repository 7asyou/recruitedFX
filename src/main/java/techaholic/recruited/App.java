package techaholic.recruited;

import java.io.IOException;
import java.util.ArrayList;

import fr.brouillard.oss.cssfx.CSSFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import techaholic.recruited.CRUD.Entite.User;
import techaholic.recruited.Utils.SideBarLoader;

/**
 * JavaFX App
 */
public class App extends Application {
	static public  User user;
	public static ArrayList<String> sidebarContent = new ArrayList<>();

	public static int[] roles = { 0, 1, 2 };

	private static Scene scene;

	public static Stage stageApp = null;

	@Override
	public void start(Stage stage) throws IOException {
		CSSFX.start();
		SideBarLoader.init(sidebarContent);
		this.stageApp=stage;
		// stage.initStyle(StageStyle.TRANSPARENT);
		scene = new Scene(loadFXML("createAccount"), 1280, 720);
		stage.setScene(scene);
		stage.show();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}
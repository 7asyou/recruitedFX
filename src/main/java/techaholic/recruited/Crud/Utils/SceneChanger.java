package techaholic.recruited.Crud.Utils;

import java.io.IOException;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import techaholic.recruited.App;

public class SceneChanger {
	@FXML
	public static void toLogin() throws IOException {
		App.setRoot("login");
	}

}
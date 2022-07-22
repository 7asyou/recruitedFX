package techaholic.recruited.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class TestingController implements Initializable {

	@FXML
	FlowPane flowPane;

	@FXML
	Text text;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			MFXButton button = new MFXButton(String.valueOf(i));
			flowPane.getChildren().addAll(button);
		}

		// flowPane.getChildren().remove();
		// flowPane.getChildren().addAll();
	}

}

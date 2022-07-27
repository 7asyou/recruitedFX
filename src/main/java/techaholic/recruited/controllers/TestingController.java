package techaholic.recruited.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class TestingController implements Initializable {

	@FXML
	FlowPane flowPane;

	@FXML
	Text text;

	@FXML
	MFXTextField input;

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			MFXButton button = new MFXButton(String.valueOf(i));
			flowPane.getChildren().addAll(button);
		}
		for (int i = 0; i < 10; i++) {
			MFXButton button = new MFXButton(String.valueOf(i));
			button.toFront();
			button.setTranslateX(-50);
			flowPane.getChildren().addAll(button);
		}

		input.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (input.focusedProperty().getValue()){
					input.getStyleClass().add("fraise");
				}
			}
			
		});
		input.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (input.focusedProperty().getValue()){
					input.getStyleClass().add("fraise");
				}
			}
			
		});

		

		
	}

}

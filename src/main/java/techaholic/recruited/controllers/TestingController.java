package techaholic.recruited.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import techaholic.recruited.Utils.Transition;

public class TestingController implements Initializable {

	@FXML
	FlowPane flowPane;

	@FXML
	Text text;

	@FXML
	MFXTextField input;

	@FXML 
	MFXButton button;

	@FXML
	Label label;

	@FXML
	Rectangle effect;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		LinearGradient gradient = new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop[]{

			new Stop(0.00, Color.color(1.0,0,1, 1.0)),
            // new Stop(0.14, Color.color(1.0,0,1, 0.85)),
            // new Stop(0.28, Color.color(1.0,0,1, 0.71)),
            new Stop(0.43, Color.color(1.0,0,1, 0.2)),
            // new Stop(0.57, Color.color(1.0,0,1, 0.43)),
            // new Stop(0.71, Color.color(1.0,0,1, 0.28)),
            // new Stop(0.85, Color.color(1.0,0,1, 0.14)),
            new Stop(1.00, Color.color(1.0,0,1, 0.0)),
		}
		);

		effect.setFill(gradient);
		

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
		
		button.setOnAction(e -> Transition.getInstance(button).play());
		
	}
	public void popUpTransition(Node node){
		TranslateTransition translateUp = new TranslateTransition(Duration.millis(200), node);
		TranslateTransition translateDown = new TranslateTransition(Duration.millis(200), node);
		translateUp.setByY(-200f);
		translateUp.setCycleCount(1);
		translateUp.setAutoReverse(true);
		translateUp.setOnFinished(e ->{
			translateDown.setByY(200f);
			translateDown.setCycleCount(1);
			ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
			service.schedule(
				translateDown::play
			, 1, TimeUnit.SECONDS);
				
		});
		translateUp.play();
	}
}

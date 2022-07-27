package techaholic.recruited.Utils;

import java.io.IOException;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Dialoge {
	
	private StackPane foregroundStack;
	private GridPane box;
	private Text title;
	private Text description;
	private MFXButton actionButton;
	private MFXButton closeButton;

	private Dialoge(String title, String description, String action){
		this.box = new GridPane();
		this.title = new Text(title);
		this.description = new Text(description);
		this.actionButton = new MFXButton(action);
		this.closeButton = new MFXButton("close");
		this.foregroundStack = new StackPane();
	}
	

		

	public void dialogInit(StackPane backgroundStack){
		
		
		// backgroundStack.getChildren().remove(foregroundStack);
		
		
		title.setText("title 1");
		description.setText("description 1");
		actionButton.setText("button 1");
		actionButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					SceneChanger.toCreateAccount();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		closeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				backgroundStack.getChildren().remove(foregroundStack);
				
			}
			
		});

	
	}
}

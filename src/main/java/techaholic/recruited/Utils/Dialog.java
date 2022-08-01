package techaholic.recruited.Utils;

import java.io.IOException;

import io.github.palexdev.materialfx.controls.MFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Dialog {	
	private StackPane foregroundStack;
	private GridPane box;
	private FlowPane foregroundFlow;
	private Text title;
	private Text description;
	private MFXButton actionButton;
	private MFXButton closeButton;

	private boolean initialized = false;

	private Dialog(){
		this.box = new GridPane();
		this.title = new Text();
		this.description = new Text();
		this.actionButton = new MFXButton();
		this.closeButton = new MFXButton("close");
		this.foregroundStack = new StackPane();
		this.foregroundFlow = new FlowPane();
	}

	private static final Dialog instance = new Dialog();

	public static Dialog getInstance(){	
		return instance;
	}		

	public void dialogInit(StackPane backgroundStack,String title, String description, String action){	
		
		
		this.title.setText(title);
		this.description.setText(description);
		this.actionButton.setText(action);
		
		if(!initialized){
			ColumnConstraints columnConstraints0 = new ColumnConstraints(100, 100, 100);
			ColumnConstraints columnConstraints1 = new ColumnConstraints(100, 100, 100);
			RowConstraints headerRowConstraits = new RowConstraints(35, 35, 35);
			RowConstraints bodyRowConstraints = new RowConstraints(100, 100, 100);
			RowConstraints footerRowConstraints = new RowConstraints(35, 35, 35);

			columnConstraints0.setPercentWidth(95);
			columnConstraints1.setPercentWidth(10);

			headerRowConstraits.setValignment(VPos.CENTER);
			bodyRowConstraints.setValignment(VPos.TOP);
				
			GridPane.setConstraints(this.title, 0, 0);
			GridPane.setConstraints(this.description, 0, 1, 1, 2);
			GridPane.setConstraints(this.actionButton, 	0, 2,1,1,HPos.LEFT,VPos.BOTTOM);
			GridPane.setConstraints(this.closeButton,1,2,1,1,HPos.RIGHT,VPos.BOTTOM);

			closeButton.setMinWidth(64);
			closeButton.setMaxWidth(64);

			box.getColumnConstraints().addAll(columnConstraints0,columnConstraints1);
			box.getRowConstraints().addAll(headerRowConstraits,bodyRowConstraints,footerRowConstraints);
			
			box.getChildren().addAll(this.title,this.description,this.actionButton,this.closeButton);
			
			foregroundFlow.getStyleClass().add("dialog-flow");

			this.foregroundStack.getChildren().addAll(foregroundFlow,box);

			box.setAlignment(Pos.CENTER);
			box.setMaxHeight(200);
			box.setMinHeight(200);
			box.setMaxWidth(300);
			box.setMinWidth(300);

			box.setPadding(new Insets(10, 20, 10, 20));

			this.box.getStyleClass().add("dialog-box");
			this.title.getStyleClass().add("dialog-title");
			this.description.getStyleClass().add("dialog-description");
			this.actionButton.getStyleClass().add("dialog-button");
			this.closeButton.getStyleClass().addAll("dialog-button","dialog-close-button");
			this.foregroundStack.getStyleClass().add("dialog-foreground");

			

			initialized = true;
		}

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
				System.out.println(closeButton.getWidth());
			}
			
		});

	
	}

	public void activate(StackPane backgroundStack){
		backgroundStack.getChildren().add(this.foregroundStack);
	}

}

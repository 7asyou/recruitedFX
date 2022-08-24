package techaholic.recruited.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXDialogs;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HorizontalDirection;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import techaholic.recruited.App;

import techaholic.recruited.CRUD.Entite.User;
import techaholic.recruited.CRUD.Service.ServiceUser;
import techaholic.recruited.Utils.Dialog;
import techaholic.recruited.Utils.Header;
import techaholic.recruited.Utils.SceneChanger;
import techaholic.recruited.Utils.Transition;
import techaholic.recruited.Utils.Validator;

public class LoginController implements Initializable {
	
	@FXML
	private MFXTextField emailField;

	@FXML
	private Pane blur;

	@FXML
	private MFXPasswordField passwordField;

	@FXML
	private MFXButton login;
	
	@FXML
	private Text loginValidation;

	@FXML
	private Text passwordValidation;
	
	@FXML
	private GridPane background;

	@FXML
	private GridPane grid;
	
	@FXML
	private Text emailMessage;
	
	@FXML
	private Text passwordMessage;
	
	@FXML
	private Text errorTitle;
	
	@FXML
	private Text errorDescription;
	
	@FXML
	private MFXButton errorButton;
	
	@FXML
	private MFXButton closeButton;

	@FXML
	private StackPane backgroundStack;

	@FXML
	private StackPane foregroundStack;
	
	@FXML
	private Label popup;

	@FXML
	private MFXFontIcon closeIcon;
	@FXML
	private MFXFontIcon focusIcon;

	@FXML
	private MFXFontIcon minimizeIcon;
	@FXML
	private HBox header;
	
	private MFXGenericDialog sqlExceptionContent;
	private MFXStageDialog sqlException;
	
	
	boolean isFocused = false;

	
	
	@FXML
	private void goToCreateAccount() throws IOException {
		App.setRoot("createAccount");
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
		Header.init(closeIcon, focusIcon, minimizeIcon, backgroundStack, header, App.stageApp);
		
		
		Dialog.getInstance().dialogInit( backgroundStack,"Error Title:", "Description: lorem episum.", "Create Account");

		
		passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent key) {
				if (key.getCode().equals(KeyCode.ENTER)){
					try {
						validateAndLogin(emailField.getText(),passwordField.getText());

					} catch (IOException e) {
						Dialog.getInstance().activate(backgroundStack);
						e.printStackTrace();
					}
				}				
			}
		});


		focusHandler(passwordField);
		focusHandler(emailField);
		
		
	}
	

	private void validateAndLogin(String email, String password) throws IOException{
		if(Validator.validatePassword(password)&&Validator.validateEmail(email)){
			ServiceUser serviceUser = ServiceUser.getInstance();

			try {
				App.user = serviceUser.findByCredential(emailField.getText().toString(), passwordField.getText().toString());
				if((App.user != null)){
					SceneChanger.toJobOffers();
				}else{
					Dialog.getInstance().activate(backgroundStack);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}else if(!Validator.validateEmail(email)){
			
			popup.setText("please enter a valid email");
			Transition.getInstance(popup).play();
			return;
		}else if(!Validator.validatePassword(password)){
			emailMessage.setText(" ");
			popup.setText("please enter a valid password");
			Transition.getInstance(popup).play();
		}
	}

	public void errorBoxHandler(){
		MFXFontIcon errorIcon = new MFXFontIcon("mfx-exclamation-circle-filled", 18);
		sqlExceptionContent.setHeaderIcon(errorIcon);
		sqlExceptionContent.setHeaderText("No Match");
	
		convertDialogTo("mfx-error-dialog");
		sqlException.showDialog();
	}

	private void convertDialogTo(String styleClass) {
		sqlExceptionContent.getStyleClass().removeIf(
				s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
		);

		if (styleClass != null)
			sqlExceptionContent.getStyleClass().add(styleClass);
	}

	private static  void focusHandler(MFXTextField t){
		ChangeListener<Boolean>  focusedListener = (obserble,oldValue,newValue) ->{
				if(newValue){
					t.setMaxHeight(55);
					t.setMinHeight(55);
				}else{
					t.setMaxHeight(40);
					t.setMinHeight(40);

		 		}
		};

		t.delegateFocusedProperty().addListener(focusedListener);

	}
	public void errorBoxInit(){
		Platform.runLater(()->{
			this.sqlExceptionContent= MFXGenericDialogBuilder.build()
									.setContentText("Please create an account!")
									.makeScrollable(false)
									.get();
			this.sqlException=MFXGenericDialogBuilder.build(this.sqlExceptionContent)
							.toStageDialogBuilder()
							.initOwner(App.stageApp)
							.initModality(Modality.WINDOW_MODAL)
							.setDraggable(false)
							.setOwnerNode(background)
							.setScrimPriority(ScrimPriority.WINDOW)
							.setScrimOwner(true)
							.get();

			sqlExceptionContent.addActions(
					Map.entry(new MFXButton("Create account"), event -> {
						try {
							SceneChanger.toCreateAccount();
							sqlException.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}),
					Map.entry(new MFXButton("close"), event -> sqlException.close())
			);

			sqlExceptionContent.setMaxSize(400, 200);
			sqlException.toFront();
			sqlExceptionContent.toFront();
			sqlException.setX(0);
			sqlException.setY(0);
		});
	}
	
	public void errorBoxInit2(){
		
		

		
		errorTitle.setText("title 1");
		errorDescription.setText("description 1");
		errorButton.setText("button 1");
		errorButton.setOnAction(new EventHandler<ActionEvent>() {

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
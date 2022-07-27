package techaholic.recruited.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.base.MFXCombo;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import techaholic.recruited.App;
import techaholic.recruited.CRUD.Entite.User;
import techaholic.recruited.CRUD.Service.ServiceUser;
import techaholic.recruited.Utils.SceneChanger;
import techaholic.recruited.Utils.Validator;

public class CreateAccountController implements Initializable {

	@FXML
	MFXTextField firstName;
	@FXML
	MFXTextField lastName;
	@FXML
	MFXTextField email;
	@FXML
	MFXPasswordField password;
	@FXML
	MFXPasswordField confirmPassword;

	@FXML
	Text invalidFirstName ;
	@FXML
	Text invalidLastName ;
	@FXML
	Text invalidEmail ;
	@FXML
	Text invalidPassword ;
	@FXML
	Text invalidConfirmPassword ;
	
	
	@FXML
	MFXButton signup;
	@FXML
	MFXButton toLogin;
	
	private String imgName;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					validateAndCreateAccount();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		toLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					SceneChanger.toLogin();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});

	}

	
	
	private void validateAndCreateAccount() throws IOException {
		if(Validator.validateWord(firstName.getText())
					&&Validator.validateWord(lastName.getText())
					&&Validator.validateEmail(email.getText())
					&&Validator.validatePassword(password.getText())
					&&confirmPassword.getText().equals(password.getText())){
			ServiceUser serviceUser = ServiceUser.getInstance();
			try {
				serviceUser.create(new User(firstName.getText(), lastName.getText(), 1, 0, email.getText(), password.getText()));
			} catch (SQLException e) {
				System.out.println("data base error");
			}
			SceneChanger.toLogin();
			return;
		}
		if(!Validator.validateWord(firstName.getText()))
			invalidFirstName.setText("invalid first name");
		if(!Validator.validateWord(lastName.getText()))
			invalidLastName.setText("invalid last name");
		if(!Validator.validateEmail(email.getText()))
			invalidEmail.setText("invalid e-mail");
		if(!Validator.validatePassword(password.getText()))
			invalidPassword.setText("invalid password");
		if(!confirmPassword.getText().equals(password.getText()))
			invalidConfirmPassword.setText("Missmatching password");

		
	}

	

	private void createImage() throws IOException {
		FileChooser fc = new FileChooser();
		File selectedFile = fc.showOpenDialog(null);
		if (selectedFile != null) {
			System.out.println(selectedFile.getAbsolutePath());
		}
		String src = selectedFile.getAbsolutePath();
		String dest = "images/" + selectedFile.getName();
		this.imgName = src;
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(dest);
			// buffer size 1K
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = is.read(buf)) > 0) {
				os.write(buf, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			is.close();
			os.close();

		}

	}


}

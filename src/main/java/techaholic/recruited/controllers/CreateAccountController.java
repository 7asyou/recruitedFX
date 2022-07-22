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

public class CreateAccountController implements Initializable {

	@FXML
	MFXTextField email;
	@FXML
	MFXTextField firstName;
	@FXML
	MFXTextField lastName;
	@FXML
	MFXTextField phone;
	MFXTextField password;

	@FXML
	MFXTextField confirm;

	@FXML
	MFXCombo<String> roles;

	@FXML
	MFXButton signin;

	private String imgName;

	@FXML
	private void goToLogin() throws IOException {
		App.setRoot("login");
	}

	private void createUser() throws SQLException, IOException {

		ServiceUser serviceUser = new ServiceUser();
		User user = new User(
				firstName.getText().toString(),
				// clastname.getText(),
				this.imgName,
				2,
				32525,
				email.getText().toString(),

				Integer.parseInt(password.getText().toString()));
		serviceUser.create(user);
		this.goToLogin();

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					createUser();
					checkLogin();
					SceneChanger.toJobOffers();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

	}

	private void checkLogin() throws IOException, SQLException {
		ServiceUser serviceUser = new ServiceUser();

		if (!email.getText().isEmpty() || !password.getText().isEmpty()) {

			System.out.println(serviceUser.findByMail(email.getText().toString()));
			App.user = serviceUser.findByMail(email.getText().toString());

			if (password.getText().toString().equals(String.valueOf(App.user.getPasswordHash()))) {

				SceneChanger.toJobOffers();
			} else {
				email.setText("");
				password.setText("");

			}

		} else if (email.getText().isEmpty() || password.getText().isEmpty()) {
			email.setText("");
			password.setText("");
		}

		else {
			email.setText("");
			password.setText("");
		}

	}

}

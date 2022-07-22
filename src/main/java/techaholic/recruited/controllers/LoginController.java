package techaholic.recruited.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.validation.Constraint;
import io.github.palexdev.materialfx.validation.Severity;
import javafx.beans.binding.Bindings;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import techaholic.recruited.App;
import techaholic.recruited.CRUD.Entite.User;
import techaholic.recruited.CRUD.Service.ServiceUser;
import techaholic.recruited.Utils.SceneChanger;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.validation.Constraint;
import io.github.palexdev.materialfx.validation.Severity;
import javafx.beans.binding.Bindings;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

	private static final PseudoClass INVALID_PSEUDO_CLASS = PseudoClass.getPseudoClass("invalid");
	// Because fuck regex, stupid shit
	private static final String[] upperChar = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" ");
	private static final String[] lowerChar = "a b c d e f g h i j k l m n o p q r s t u v w x y z".split(" ");
	private static final String[] digits = "0 1 2 3 4 5 6 7 8 9".split(" ");
	private static final String[] specialCharacters = "! @ # & ( ) – [ { } ]: ; ' , ? / * ~ $ ^ + = < > -".split(" ");
	@FXML
	private MFXTextField emailField;

	@FXML
	private MFXPasswordField passwordField;

	@FXML
	private Label validationLabel;
	@FXML
	private Label emailValid;

	@FXML
	private MFXButton login;

	@FXML
	private void goToCreateAccount() throws IOException {
		App.setRoot("createAccount");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Constraint lengthConstraint = Constraint.Builder.build()
				.setSeverity(Severity.ERROR)
				.setMessage("Password must be at least 8 characters long")
				.setCondition(passwordField.textProperty().length().greaterThanOrEqualTo(8))
				.get();

		passwordField.getValidator().constraint(lengthConstraint);
		emailField.getValidator().constraint(lengthConstraint);

		passwordField.getValidator().validProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				validationLabel.setVisible(false);
				passwordField.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, false);
			}
		});

		emailField.getValidator().validProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				emailValid.setVisible(false);
				emailField.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, false);
			}
		});

		login.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				try {
					checkLogin();
					SceneChanger.toEvents();
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

		if (!emailField.getText().isEmpty() || !passwordField.getText().isEmpty()) {
			// if the email field or the password field is empty then
			System.out.println(serviceUser.findByMail(emailField.getText().toString()));
			App.user = serviceUser.findByMail(emailField.getText().toString());
			// cons
			if (passwordField.getText().toString().equals(String.valueOf(App.user.getPasswordHash()))) {

				SceneChanger.toJobOffers();
			} else {
				emailField.setText("");
				passwordField.setText("");

			}

		} else if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
			emailField.setText("");
			passwordField.setText("");
		}

		else {
			emailField.setText("");
			passwordField.setText("");
		}

	}
}
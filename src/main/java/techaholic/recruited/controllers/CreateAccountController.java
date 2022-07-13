package techaholic.recruited.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import techaholic.recruited.App;

public class CreateAccountController {

	@FXML
	private void goToLogin() throws IOException {
		App.setRoot("login");
	}
}

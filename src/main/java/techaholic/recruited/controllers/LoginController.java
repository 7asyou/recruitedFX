package techaholic.recruited.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import techaholic.recruited.App;

public class LoginController {

	@FXML
	private void goToCreateAccount() throws IOException {
		App.setRoot("createAccount");
	}
}
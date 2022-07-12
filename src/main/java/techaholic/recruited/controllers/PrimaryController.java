package techaholic.recruited.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import techaholic.recruited.App;

public class PrimaryController {

	@FXML
	private void switchToSecondary() throws IOException {
		App.setRoot("secondary");
	}
}

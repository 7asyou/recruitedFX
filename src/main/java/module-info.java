module techaholic.recruited {
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires fr.brouillard.oss.cssfx;

	opens techaholic.recruited to javafx.fxml;
	opens techaholic.recruited.controllers to javafx.fxml;

	exports techaholic.recruited;
}

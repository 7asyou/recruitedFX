module techaholic.recruited {
	requires javafx.controls;
	requires javafx.fxml;
	requires fr.brouillard.oss.cssfx;

	opens techaholic.recruited to javafx.fxml;

	exports techaholic.recruited;
}

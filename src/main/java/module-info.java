module techaholic.recruited {
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires fr.brouillard.oss.cssfx;
	requires courier.java;
	requires java.sql;
	requires commons.validator;
	requires jidefx.common;
	requires VirtualizedFX;
	requires MaterialFX;

	opens techaholic.recruited to javafx.fxml;
	opens techaholic.recruited.controllers to javafx.fxml;

	exports techaholic.recruited;
}

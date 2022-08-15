package techaholic.recruited.Utils;

import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.css.PseudoClass;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Header {
	
	private static double xOffset;
	private static double yOffset;
	public static void init(MFXFontIcon closeIcon, MFXFontIcon focusIcon, MFXFontIcon minimizeIcon, Pane rootPane, HBox header,Stage stage){
		
		closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
		minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED,
				event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
		focusIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			boolean newVal = !stage.isAlwaysOnTop();
			focusIcon.pseudoClassStateChanged(PseudoClass.getPseudoClass("always-on-top"), newVal);
			stage.setAlwaysOnTop(newVal);
		});

		header.setOnMousePressed(event -> {
			xOffset = stage.getX() - event.getScreenX();
			yOffset = stage.getY() - event.getScreenY();
		});
		header.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() + xOffset);
			stage.setY(event.getScreenY() + yOffset);
		});
	}
}

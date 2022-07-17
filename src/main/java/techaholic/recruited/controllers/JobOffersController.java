package techaholic.recruited.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import techaholic.recruited.App;
import techaholic.recruited.Crud.Entite.JobOffer;
import techaholic.recruited.Crud.Service.ServiceJobOffer;

public class JobOffersController implements Initializable {

	@FXML
	FlowPane flowPane;
	@FXML
	Label label;
	@FXML
	MFXScrollPane scrollPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {

			ServiceJobOffer serviceJobOffer = new ServiceJobOffer();
			ObservableList<JobOffer> jobOffers = FXCollections.observableArrayList(serviceJobOffer.getAll());
			jobOffers.forEach(jobOffer -> {
				flowPane.setOrientation(Orientation.VERTICAL);
				FlowPane cell = new FlowPane(Orientation.VERTICAL);
				FlowPane cellHeader = new FlowPane();
				FlowPane cellBody = new FlowPane();
				FlowPane cellFooter = new FlowPane();

				FlowPane number = new FlowPane(Orientation.VERTICAL);

				Label positionTitle = new Label(jobOffer.getPositionTitle());
				Label availablePositions = new Label("n av. " + String.valueOf(jobOffer.getAvailablePositions()));
				Label description = new Label(jobOffer.getDescription());
				MFXButton addButton = new MFXButton("add");

				cellHeader.getChildren().addAll(positionTitle, availablePositions);
				cellBody.getChildren().addAll(description);
				cellFooter.getChildren().addAll(addButton);
				cell.getChildren().addAll(cellHeader, cellBody, cellFooter);

				cellHeader.getStyleClass().clear();
				cellBody.getStyleClass().clear();
				cellFooter.getStyleClass().clear();
				cell.getStyleClass().clear();
				cellHeader.getStyleClass().add("cell-header");
				cellBody.getStyleClass().add("cell-body");
				cellFooter.getStyleClass().add("cell-footer");
				// cell.getStyleClass().add("cell");

				flowPane.setMaxSize(100, Double.MAX_VALUE);

				flowPane.getChildren().add(cell);
			});
			scrollPane.setContent(flowPane);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

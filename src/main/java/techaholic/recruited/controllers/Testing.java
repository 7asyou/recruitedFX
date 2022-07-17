package techaholic.recruited.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;

import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Orientation;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;

import javafx.scene.text.Text;

import techaholic.recruited.Crud.Entite.JobOffer;
import techaholic.recruited.Crud.Service.ServiceJobOffer;

import techaholic.recruited.Crud.Utils.SideBarLoader;

public class Testing implements Initializable {

	@FXML
	FlowPane flowPane;

	@FXML
	FlowPane sideBar;

	@FXML
	MFXScrollPane listScrollPane;

	@FXML
	MFXButton addButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ServiceJobOffer serviceJobOffer = new ServiceJobOffer();

		ScrollUtils.addSmoothScrolling(listScrollPane);

		try {
			ArrayList<JobOffer> jobOffers = serviceJobOffer.getAll();
			jobOffers.forEach(jobOffer -> {

				FlowPane cell = new FlowPane(Orientation.VERTICAL);
				GridPane cellHeader = new GridPane();
				FlowPane cellBody = new FlowPane();
				GridPane cellFooter = new GridPane();

				MFXButton delete = new MFXButton("", new MFXFontIcon("mfx-delete-alt", 25, Color.valueOf("#4f77AA")));

				Text title = new Text(jobOffer.getPositionTitle());
				Text availablePosition = new Text("n av. " + String.valueOf(jobOffer.getAvailablePositions()));
				Text desc = new Text(jobOffer.getDescription());
				desc.setWrappingWidth(700);
				title.getStyleClass().add("text-content");
				availablePosition.getStyleClass().add("text-content");
				desc.getStyleClass().add("text-content");

				MFXButton applyButton = new MFXButton("apply");
				MFXButton editButton = new MFXButton("edit");

				ColumnConstraints columnConstraints0 = new ColumnConstraints();
				columnConstraints0.setPercentWidth(95);
				ColumnConstraints columnConstraints1 = new ColumnConstraints();
				columnConstraints1.setPercentWidth(10);
				ColumnConstraints columnConstraints2 = new ColumnConstraints();
				columnConstraints2.setPercentWidth(1);
				ColumnConstraints columnConstraints3 = new ColumnConstraints();
				columnConstraints3.setPercentWidth(1);

				cellHeader.getColumnConstraints().addAll(columnConstraints0, columnConstraints1);
				cellFooter.getColumnConstraints().addAll(columnConstraints3, columnConstraints2);

				GridPane.setConstraints(title, 0, 0);
				GridPane.setConstraints(availablePosition, 1, 0);
				cellHeader.getChildren().addAll(title, availablePosition);
				cellBody.getChildren().add(desc);
				GridPane.setConstraints(editButton, 0, 0);
				GridPane.setConstraints(applyButton, 2, 0);
				cellFooter.getChildren().addAll(editButton, applyButton);

				cell.getChildren().addAll(cellHeader, cellBody, cellFooter);
				cell.getStyleClass().add("cell");
				cellHeader.getStyleClass().add("cell-header");
				cellBody.getStyleClass().add("cell-body");
				cellFooter.getStyleClass().add("cell-footer");

				delete.getStyleClass().addAll("delete", "list-button");
				applyButton.getStyleClass().add("list-button");
				editButton.getStyleClass().add("list-button");
				desc.getStyleClass().add("desc");

				GridPane finalCell = new GridPane();
				GridPane.setConstraints(delete, 0, 0);
				GridPane.setConstraints(cell, 0, 1);
				finalCell.getChildren().addAll(cell, delete);
				flowPane.getChildren().addAll(finalCell);
			});
			int vGap = 5;
			flowPane.setVgap(vGap);
			flowPane.setPrefSize(776, jobOffers.size() * (268 + 35 + vGap) - vGap);
			flowPane.setMaxSize(776, jobOffers.size() * (268 + 35 + vGap) - vGap);
			flowPane.setMinSize(776, jobOffers.size() * (268 + 35 + vGap) - vGap);

			SideBarLoader.sideBarFill(sideBar);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

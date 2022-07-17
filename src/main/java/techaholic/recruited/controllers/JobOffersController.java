package techaholic.recruited.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.function.Consumer;

// import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;

import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import javafx.scene.text.Text;

import techaholic.recruited.Crud.Entite.JobOffer;
import techaholic.recruited.Crud.Service.ServiceJobOffer;

import techaholic.recruited.Crud.Utils.SideBarLoader;

public class JobOffersController implements Initializable {

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

				GridPane finalCell = createCell(jobOffer);

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

	public GridPane createCell(JobOffer jobOffer) {
		GridPane finalCell = new GridPane();
		FlowPane cell = new FlowPane(Orientation.VERTICAL);

		GridPane cellHeader = createCellHeader(jobOffer.getPositionTitle(),
				jobOffer.getAvailablePositions());

		FlowPane cellBody = createCellBody(jobOffer.getDescription(), jobOffer.getTags());

		GridPane cellFooter = createCellFooter();

		MFXButton delete = new MFXButton("", new MFXFontIcon("mfx-delete-alt", 25, Color.valueOf("#4f77AA")));

		cell.getChildren().addAll(cellHeader, cellBody, cellFooter);
		cell.getStyleClass().add("cell");

		cellFooter.getStyleClass().add("cell-footer");

		delete.getStyleClass().addAll("delete", "list-button");

		GridPane.setConstraints(delete, 0, 0);
		GridPane.setConstraints(cell, 0, 1);
		finalCell.getChildren().addAll(cell, delete);

		return finalCell;
	}

	public GridPane createCellHeader(String titleContent, int availblePositonContent) {
		Text title = new Text(titleContent);
		Text availablePosition = new Text("n av. " + String.valueOf(availblePositonContent));

		title.getStyleClass().add("text-content");
		availablePosition.getStyleClass().add("text-content");

		ColumnConstraints columnConstraints0 = new ColumnConstraints();
		ColumnConstraints columnConstraints1 = new ColumnConstraints();

		columnConstraints0.setPercentWidth(95);
		columnConstraints1.setPercentWidth(10);

		GridPane.setConstraints(title, 0, 0);
		GridPane.setConstraints(availablePosition, 1, 0);
		GridPane cellHeader = new GridPane();

		cellHeader.getColumnConstraints().addAll(columnConstraints0, columnConstraints1);

		cellHeader.getChildren().addAll(title, availablePosition);

		cellHeader.getStyleClass().add("cell-header");

		return cellHeader;
	}

	public FlowPane createCellBody(String descriptionContent, String tagsContent) {
		FlowPane tags = createTags(tagsContent);
		Text desc = new Text(descriptionContent);

		desc.setWrappingWidth(700);
		desc.getStyleClass().addAll("text-content", "desc");

		FlowPane cellBody = new FlowPane();

		cellBody.getChildren().addAll(tags);
		cellBody.getChildren().addAll(desc);

		cellBody.getStyleClass().add("cell-body");

		return cellBody;
	}

	public GridPane createCellFooter() {
		MFXButton applyButton = new MFXButton("apply");
		MFXButton editButton = new MFXButton("edit");

		applyButton.getStyleClass().add("list-button");
		editButton.getStyleClass().add("list-button");

		ColumnConstraints columnConstraints2 = new ColumnConstraints();
		columnConstraints2.setPercentWidth(1);

		GridPane.setConstraints(editButton, 0, 0);
		GridPane.setConstraints(applyButton, 2, 0);

		GridPane cellFooter = new GridPane();

		cellFooter.getChildren().addAll(editButton, applyButton);
		cellFooter.getColumnConstraints().addAll(columnConstraints2, columnConstraints2);

		cellFooter.getStyleClass().add("cell-footer");

		return cellFooter;
	}

	public FlowPane createTags(String tagsContent) {
		ArrayList<String> tagsContentList = new ArrayList<>();
		Collections.addAll(tagsContentList, tagsContent.split(" "));

		ArrayList<Label> tagsArrayList = new ArrayList<>();

		for (String tag : tagsContentList) {
			Label tagLabel = new Label(tag);
			tagLabel.getStyleClass().add("tag");
			Region icon = new Region();
			icon.getStyleClass().add("tag-icon");
			tagLabel.setGraphic(icon);
			tagsArrayList.add(tagLabel);
		}
		ObservableList<Label> tagsObservableList = FXCollections.observableArrayList(tagsArrayList);
		FlowPane tags = new FlowPane();
		tags.getChildren().addAll(tagsObservableList);
		tags.setHgap(5);
		tags.getChildren().forEach(child -> {
			tags.setMargin(child, new Insets(0, 0, 5, 0));
		});
		return tags;
	}

}

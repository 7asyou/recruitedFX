package techaholic.recruited.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Consumer;

// import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;

import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import techaholic.recruited.CRUD.Entite.Event;
import techaholic.recruited.CRUD.Entite.JobOffer;
import techaholic.recruited.CRUD.Service.ServiceEvent;
import techaholic.recruited.CRUD.Service.ServiceJobOffer;
import techaholic.recruited.Utils.SceneChanger;
import techaholic.recruited.Utils.SideBarLoader;

public class EventsController implements Initializable {

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

		ServiceEvent serviceEvent = new ServiceEvent();

		ScrollUtils.addSmoothScrolling(listScrollPane);

		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					SceneChanger.toCreateEvent();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		try {
			ArrayList<Event> events = serviceEvent.getAll();
			events.forEach(event -> {

				GridPane finalCell = createCell(event);

				flowPane.getChildren().addAll(finalCell);
			});
			int vGap = 5;
			flowPane.setVgap(vGap);
			flowPane.setPrefSize(776, events.size() * (268 + 35 + vGap) - vGap);
			flowPane.setMaxSize(776, events.size() * (268 + 35 + vGap) - vGap);
			flowPane.setMinSize(776, events.size() * (268 + 35 + vGap) - vGap);

			SideBarLoader.sideBarFill(sideBar);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public GridPane createCell(Event thisEvent) {
		GridPane finalCell = new GridPane();
		FlowPane cell = new FlowPane(Orientation.VERTICAL);

		GridPane cellHeader = createCellHeader(thisEvent.getName());

		FlowPane cellBody = createCellBody(
				thisEvent.getDescription(),
				thisEvent.getTags(),
				thisEvent.getDate());

		GridPane cellFooter = createCellFooter(thisEvent.getId());

		MFXButton delete = new MFXButton("", new MFXFontIcon("mfx-delete-alt", 25, Color.valueOf("#4f77AA")));
		delete.getStyleClass().addAll("delete", "list-button");

		delete.setId(String.valueOf(thisEvent.getId()));
		delete.setOnAction(new EventHandler<ActionEvent>() {

			ServiceEvent serviceEvent = new ServiceEvent();

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					serviceEvent.delete(Integer.valueOf(delete.getId()));
					SceneChanger.toEvents();
				} catch (NumberFormatException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

		cell.getChildren().addAll(cellHeader, cellBody, cellFooter);
		cell.getStyleClass().add("cell");

		cellFooter.getStyleClass().add("cell-footer");

		GridPane.setConstraints(delete, 0, 0);
		GridPane.setConstraints(cell, 0, 1);
		finalCell.getChildren().addAll(cell, delete);

		return finalCell;
	}

	public GridPane createCellHeader(String titleContent) {
		Text title = new Text(titleContent);

		title.getStyleClass().add("text-content");

		ColumnConstraints columnConstraints0 = new ColumnConstraints();
		ColumnConstraints columnConstraints1 = new ColumnConstraints();

		columnConstraints0.setPercentWidth(95);
		columnConstraints1.setPercentWidth(10);

		GridPane.setConstraints(title, 0, 0);
		GridPane cellHeader = new GridPane();

		cellHeader.getColumnConstraints().addAll(columnConstraints0, columnConstraints1);

		cellHeader.getChildren().addAll(title);

		cellHeader.getStyleClass().add("cell-header");

		return cellHeader;
	}

	public FlowPane createCellBody(String descriptionContent, String tagsContent, Date deadlineDate) {
		FlowPane tags = createTags(tagsContent);
		Text desc = new Text(descriptionContent);

		desc.setWrappingWidth(700);
		desc.getStyleClass().addAll("text-content", "desc");

		Locale locale = new Locale("en");

		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);

		String dateString = dateFormat.format(deadlineDate);

		Text deadline = new Text(dateString + ".");

		deadline.setFontSmoothingType(FontSmoothingType.GRAY);
		deadline.getStyleClass().add("date-location");

		FlowPane cellBody = new FlowPane(Orientation.VERTICAL);

		cellBody.getChildren().addAll(tags);
		cellBody.getChildren().addAll(deadline);
		cellBody.getChildren().addAll(desc);

		cellBody.getStyleClass().add("cell-body");

		return cellBody;
	}

	public GridPane createCellFooter(int id) {
		MFXButton applyButton = new MFXButton("apply");
		MFXButton editButton = new MFXButton("edit");

		applyButton.setId(String.valueOf(id));
		editButton.setId(String.valueOf(id));

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

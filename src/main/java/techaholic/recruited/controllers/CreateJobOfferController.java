package techaholic.recruited.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXSlider;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.base.MFXCombo;
import io.github.palexdev.materialfx.enums.FloatMode;
import io.github.palexdev.materialfx.validation.Constraint;
import io.github.palexdev.materialfx.validation.Severity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import techaholic.recruited.CRUD.Entite.JobOffer;
import techaholic.recruited.CRUD.Service.ServiceJobOffer;
import techaholic.recruited.Utils.Locations;
import techaholic.recruited.Utils.SceneChanger;
import techaholic.recruited.Utils.SideBarLoader;

public class CreateJobOfferController implements Initializable {

	@FXML
	FlowPane sidebar;
	@FXML
	MFXTextField positionTitle;
	@FXML
	MFXSlider availablePositions;
	@FXML
	MFXCombo<String> locations;
	@FXML
	MFXButton addTags;
	@FXML
	FlowPane tagsPane;
	@FXML
	MFXDatePicker deadline;
	@FXML
	MFXSlider duration;
	@FXML
	TextField description;
	@FXML
	MFXButton add;

	@FXML
	Label positionNotValid;
	@FXML
	Text locationVal;
	@FXML
	Text descriptionVal;

	private static final PseudoClass INVALID_PSEUDO_CLASS = PseudoClass.getPseudoClass("invalid");
	boolean valid = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SideBarLoader.sideBarFill(sidebar);

		positionNotValid.setVisible(false);
		locationVal.setVisible(false);
		descriptionVal.setVisible(false);

		locations.setItems(Locations.locations());
		ObservableList<MFXTextField> tagsObservableList = FXCollections.observableArrayList(new ArrayList<>());
		deadline.setValue(deadline.getCurrentDate());
		addTags.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MFXTextField tag = new MFXTextField();
				FlowPane field = new FlowPane();
				Label tagLabel = new Label();
				Region icon = new Region();

				tagLabel.setGraphic(icon);

				tag.setFloatMode(FloatMode.DISABLED);
				field.getChildren().addAll(tagLabel, tag);

				tag.getStyleClass().add("add-tag");
				field.getStyleClass().add("add-tag-field");
				icon.getStyleClass().add("tag-icon");
				tagLabel.getStyleClass().add("tag");

				tagsPane.getChildren().add(field);
				tagsObservableList.add(tag);
			}

		});

		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String positionTitleValue = positionTitle.getText();
				int availablePositionsValue = (int) availablePositions.getValue();
				String locationValue = locations.getValue();

				String tags = "";
				for (int i = 0; i < tagsObservableList.size(); i++) {
					if (i != 0) {
						tags = tags + " ";
					}
					tags = tags + tagsObservableList.get(i).getText();
				}
				String deadlineValue = deadline.getValue().toString();
				int durationValue = (int) duration.getValue();
				String descriptionValue = description.getText();

				if (positionTitleValue.length() <= 0) {
					positionNotValid.setVisible(true);
					valid = false;
				}
				if (locations.getValue().length() <= 0) {
					locationVal.setVisible(true);
				}
				if (description.getText().length() <= 0) {
					descriptionVal.setVisible(true);
				}

				JobOffer jobOffer = new JobOffer(
						positionTitleValue,
						availablePositionsValue,
						locationValue,
						tags,
						Date.valueOf(deadlineValue),
						durationValue,
						descriptionValue);

				if (valid) {
					ServiceJobOffer serviceJobOffer = new ServiceJobOffer();
					try {
						serviceJobOffer.create(jobOffer);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
	}

}

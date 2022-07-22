package techaholic.recruited.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.ResourceBundle;

import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import models.SendRequestMessageRouting;
import services.Courier;
import services.SendService;

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
import techaholic.recruited.App;
import techaholic.recruited.CRUD.Entite.*;
import techaholic.recruited.CRUD.Service.*;
import techaholic.recruited.Utils.Locations;
import techaholic.recruited.Utils.SceneChanger;
import techaholic.recruited.Utils.SideBarLoader;

public class CreateEventController implements Initializable {

	@FXML
	FlowPane sidebar;
	@FXML
	MFXTextField positionTitle;

	@FXML
	MFXButton addTags;
	@FXML
	FlowPane tagsPane;
	@FXML
	MFXDatePicker deadline;

	@FXML
	TextField description;
	@FXML
	MFXButton add;

	@FXML
	Label positionNotValid;

	@FXML
	Label descriptionVal;

	private static final PseudoClass INVALID_PSEUDO_CLASS = PseudoClass.getPseudoClass("invalid");
	boolean valid = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SideBarLoader.sideBarFill(sidebar);

		positionNotValid.setVisible(false);

		descriptionVal.setVisible(false);

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

				String tags = "";
				for (int i = 0; i < tagsObservableList.size(); i++) {
					if (i != 0) {
						tags = tags + " ";
					}
					tags = tags + tagsObservableList.get(i).getText();
				}
				String deadlineValue = deadline.getValue().toString();

				String descriptionValue = description.getText();

				if (positionTitleValue.length() <= 0) {
					positionNotValid.setVisible(true);
					valid = false;
				}
				// if (locations.getValue().length() <= 0) {
				// locationVal.setVisible(true);
				// valid = false;
				// }
				if (description.getText().length() <= 0) {
					descriptionVal.setVisible(true);
				}

				Event thisEvent = new Event(positionTitleValue, Date.valueOf(deadlineValue), tags, "images",
						descriptionValue);

				if (valid) {
					ServiceEvent serviceEvent = new ServiceEvent();
					try {
						serviceEvent.create(thisEvent);
						SceneChanger.toEvents();
						sendEmail("Event Created:" + thisEvent.getName(), thisEvent.getDescription());

					} catch (SQLException | IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
	}

	private void sendEmail(String title, String description) throws InterruptedException {
		Courier.init("pk_prod_BV7MG0NYW1MBFNHXYA2MPM2JW4ME");

		SendEnhancedRequestBody sendEnhancedRequestBody = new SendEnhancedRequestBody();
		SendRequestMessage sendRequestMessage = new SendRequestMessage();
		HashMap<String, String> to = new HashMap<String, String>();
		to.put("email", App.user.getEmail());
		sendRequestMessage.setTo(to);

		List<Object> routingChannels = new ArrayList<Object>();
		routingChannels.add("email");

		SendRequestMessageRouting routing = new SendRequestMessageRouting();
		routing.setMethod("single");
		routing.setChannels(routingChannels);

		sendRequestMessage.setRouting(routing);
		List<String> provider = new ArrayList<String>();
		provider.add("gmail");

		HashMap<String, Object> emailProviders = new HashMap<String, Object>();
		emailProviders.put("providers", provider);

		HashMap<String, Object> channels = new HashMap<String, Object>();
		channels.put("email", emailProviders);
		sendRequestMessage.setChannels(channels);
		// !
		HashMap<String, String> content = new HashMap<String, String>();
		content.put("title", title);
		content.put("body", description);
		sendRequestMessage.setContent(content);
		// !
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("joke", "Why do Java programmers have to wear glasses? Because they don't C#");
		sendRequestMessage.setData(data);
		sendEnhancedRequestBody.setMessage(sendRequestMessage);

		try {
			SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(sendEnhancedRequestBody);
			System.out.println(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

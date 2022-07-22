package techaholic.recruited.Utils;

import java.io.IOException;

import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import techaholic.recruited.App;

public class SceneChanger {
	@FXML
	public static void toLogin() throws IOException {
		App.setRoot("login");
	}

	public static void toJobOffers() throws IOException {
		App.setRoot("jobOffers");
	}

	public static void toCreateJobOffer() throws IOException {
		App.setRoot("createJobOffer");
	}

	public static void toArticles() throws IOException {
		App.setRoot("articles");
	}

	public static void toCreateArticle() throws IOException {
		App.setRoot("createArticle");
	}

	public static void toEntreprises() throws IOException {
		App.setRoot("entreprises");
	}

	public static void toCreateEntreprise() throws IOException {
		App.setRoot("createEntreprise");
	}

	public static void toEvents() throws IOException {
		App.setRoot("events");
	}

	public static void toCreateEvent() throws IOException {
		App.setRoot("createEvent");
	}

	public static void toJobApplications() throws IOException {
		App.setRoot("jobApplications");
	}

	public static void toComplaints() throws IOException {
		App.setRoot("complaints");
	}

	public static void toCreateComplaint() throws IOException {
		App.setRoot("createComplaint");
	}

	public static void toUsers() throws IOException {
		App.setRoot("users");
	}

}
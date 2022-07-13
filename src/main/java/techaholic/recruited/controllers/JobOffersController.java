package techaholic.recruited.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import techaholic.recruited.App;
import techaholic.recruited.Crud.Entite.JobOffer;
import techaholic.recruited.Crud.Service.ServiceJobOffer;

public class JobOffersController implements Initializable {

	@FXML
	private MFXListView<JobOffer> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ServiceJobOffer serviceJobOffer = new ServiceJobOffer();

		try {
			ObservableList<JobOffer> jobOffers = FXCollections.observableArrayList(serviceJobOffer.getAll());
			StringConverter<JobOffer> converter = FunctionalStringConverter
					.to(jobOffer -> (jobOffer == null) ? "" : " ");
			// .to(jobOffer -> (jobOffer == null) ? "" : jobOffer.get() + " " +
			// jobOffer.getSurname());

			list.setItems(jobOffers);
			list.setConverter(converter);
			list.setCellFactory(jobOffer -> new JobOfferCellFactory(list, jobOffer));
			list.features().enableBounceEffect();
			list.features().enableSmoothScrolling(0.5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static class JobOfferCellFactory extends MFXListCell<JobOffer> {
		private final MFXFontIcon userIcon;

		public JobOfferCellFactory(MFXListView<JobOffer> listView, JobOffer data) {
			super(listView, data);

			userIcon = new MFXFontIcon("mfx-user", 18);
			userIcon.getStyleClass().add("user-icon");
			render(data);
		}

		@Override
		protected void render(JobOffer data) {
			super.render(data);
			if (userIcon != null)
				getChildren().add(0, userIcon);
		}
	}
}

package techaholic.recruited.Crud.Utils;

import java.io.IOException;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import techaholic.recruited.App;

/**
 * SideBarLoader
 */
public class SideBarLoader {

	public static void init(ArrayList<String> sidebarContent) {
		if (App.role == "admin") {
			sidebarContent.add("Users");
		}
		sidebarContent.add("Job Offers");
		sidebarContent.add("Companies");
		sidebarContent.add("Events");
		sidebarContent.add("Articles");
		sidebarContent.add("Complaints");
	}

	public static void sideBarFill(FlowPane sideBar) {
		App.sidebarContent.forEach(element -> {
			MFXButton elementButton = new MFXButton(element);
			elementButton.setAlignment(Pos.CENTER_LEFT);
			elementButton.setPadding(new Insets(10, 0, 10, 80));
			elementButton.getStyleClass().add("sidebar-button");

			switch (element) {
				case "Users":
					elementButton.setOnAction(e -> {

						ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
						service.schedule(() -> {
							try {
								SceneChanger.toLogin();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}, 1, TimeUnit.MICROSECONDS);

					});
					break;
				case "Job Offers":
					elementButton.setOnAction(e -> {

						ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
						service.schedule(() -> {
							try {
								SceneChanger.toLogin();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}, 1, TimeUnit.MICROSECONDS);

					});
					break;
				case "Companies":
					elementButton.setOnAction(e -> {

						ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
						service.schedule(() -> {
							try {
								SceneChanger.toLogin();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}, 1, TimeUnit.MICROSECONDS);

					});
					break;
				case "Events":
					elementButton.setOnAction(e -> {

						ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
						service.schedule(() -> {
							try {
								SceneChanger.toLogin();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}, 1, TimeUnit.MICROSECONDS);

					});
					break;
				case "Article":
					elementButton.setOnAction(e -> {

						ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
						service.schedule(() -> {
							try {
								SceneChanger.toLogin();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}, 1, TimeUnit.MICROSECONDS);

					});
					break;
				case "Complaints":
					elementButton.setOnAction(e -> {

						ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
						service.schedule(() -> {
							try {
								SceneChanger.toLogin();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}, 1, TimeUnit.NANOSECONDS);

					});
					break;

				default:
					break;
			}

			sideBar.getChildren().add(elementButton);
		});
	}

}
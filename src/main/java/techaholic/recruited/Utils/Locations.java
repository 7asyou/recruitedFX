package techaholic.recruited.Utils;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Locations {

	public static ObservableList<String> locations() {
		ArrayList<String> locationsArrayList = new ArrayList<>();
		String[] locationsString = { "Tunis", "Centre Urban", "Lac" };
		for (String location : locationsString) {
			locationsArrayList.add(location);
		}
		ObservableList<String> locations = FXCollections.observableArrayList(locationsArrayList);

		return locations;
	}

}

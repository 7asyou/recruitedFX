package techaholic.recruited.Crud.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import techaholic.recruited.Crud.Entite.Event;
import techaholic.recruited.Utils.DataSource;

public class ServiceEvent implements
		IService<Event> {
	private PreparedStatement preparedStatement;
	private Statement statement;
	private Connection connection = DataSource.getInstance().getCon();
	private ResultSet resultSet;

	public ServiceEvent() {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void create(Event event) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"INSERT INTO `event`(`name`, `date`, `tags`, `images`, `description`) VALUES (?,?,?,?,?)");
		preparedStatement.setString(1, event.getName());
		preparedStatement.setDate(2, event.getDate());
		preparedStatement.setString(3, event.getTags());
		preparedStatement.setString(4, event.getImages());
		preparedStatement.setString(5, event.getDescription());
		preparedStatement.executeUpdate();
		System.out.println("event added");

	}

	@Override
	public void update(Event event, int id) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"UPDATE `event` SET `name`=?,`date`=?,`tags`=?,`images`=?,`description`=?  WHERE id="
						+
						id);
		preparedStatement.setString(1, event.getName());
		preparedStatement.setDate(2, event.getDate());
		preparedStatement.setString(3, event.getTags());
		preparedStatement.setString(4, event.getImages());
		preparedStatement.setString(5, event.getDescription());
		preparedStatement.executeUpdate();
		System.out.println("event updated");

	}

	@Override
	public void delete(int id) throws SQLException {

		preparedStatement = connection.prepareStatement("DELETE FROM `event` WHERE id=" + id);
		preparedStatement.executeUpdate();
		System.out.println("event deleted");

	}

	@Override
	public Event findById(int id) throws SQLException {
		resultSet = statement.executeQuery("SELECT * FROM `event` WHERE id=" + id);
		if (resultSet.next()) {
			return new Event(resultSet.getInt("id"),
					resultSet.getString(2),
					resultSet.getDate(3),
					resultSet.getString(4),
					resultSet.getString(5),
					resultSet.getString(6));
		} else
			return null;
	}

	@Override
	public ArrayList<Event> getAll() throws SQLException {
		ArrayList<Event> events = new ArrayList<>();
		resultSet = statement.executeQuery("SELECT * FROM `event`");
		while (resultSet.next()) {
			events.add(new Event(resultSet.getInt("id"),
					resultSet.getString(2),
					resultSet.getDate(3),
					resultSet.getString(4),
					resultSet.getString(5),
					resultSet.getString(6)));
		}
		return events;
	}

}

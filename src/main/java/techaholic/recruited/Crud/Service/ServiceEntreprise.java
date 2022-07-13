package techaholic.recruited.Crud.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import techaholic.recruited.Crud.Utils.DataSource;
import techaholic.recruited.Crud.Entite.Entreprise;
// import javafx.concurrent.Service;

public class ServiceEntreprise implements IService<Entreprise> {
	private PreparedStatement preparedStatement;
	private Statement statement;
	private Connection connection = DataSource.getInstance().getCon();
	private ResultSet resultSet;

	public ServiceEntreprise() {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void create(Entreprise entreprise) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"INSERT INTO `entreprise`( `name`, `tags`, `logo`, `contact_number`, `contact_email`, `description`) VALUES (?,?,?,?,?,?)");
		preparedStatement.setString(1, entreprise.getName());
		preparedStatement.setString(2, entreprise.getTags());
		preparedStatement.setString(3, entreprise.getLogo());
		preparedStatement.setString(4, entreprise.getContactNumber());
		preparedStatement.setString(5, entreprise.getContactEmail());
		preparedStatement.setString(6, entreprise.getDescription());
		preparedStatement.executeUpdate();
		System.out.println("entreprise added");

	}

	@Override
	public void update(Entreprise entreprise, int id) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"UPDATE `entreprise` SET `name`=?,`tags`=?,`logo`=?,`contact_number`=?,`contact_email`=?,`description`=? WHERE id ="
						+ id);
		preparedStatement.setString(1, entreprise.getName());
		preparedStatement.setString(2, entreprise.getTags());
		preparedStatement.setString(3, entreprise.getLogo());
		preparedStatement.setString(4, entreprise.getContactNumber());
		preparedStatement.setString(5, entreprise.getContactEmail());
		preparedStatement.setString(6, entreprise.getDescription());
		preparedStatement.executeUpdate();
		System.out.println("entreprise updated");

	}

	@Override
	public void delete(int id) throws SQLException {
		preparedStatement = connection.prepareStatement("DELETE FROM `entreprise` WHERE id=" + id);
		preparedStatement.executeUpdate();
		System.out.println("entreprise deleted");

	}

	@Override
	public Entreprise findById(int id) throws SQLException {
		resultSet = statement.executeQuery("SELECT * FROM entreprise WHERE id=" + id);
		if (resultSet.next()) {
			return new Entreprise(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
		} else
			return null;
	}

	@Override
	public ArrayList<Entreprise> getAll() throws SQLException {
		ArrayList<Entreprise> entreprises = new ArrayList<>();
		resultSet = statement.executeQuery("SELECT * FROM entreprise");
		while (resultSet.next()) {
			entreprises.add(new Entreprise(resultSet.getInt("id"), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
		}
		return entreprises;
	}

}

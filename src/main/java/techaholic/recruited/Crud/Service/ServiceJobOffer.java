package techaholic.recruited.CRUD.Service;

import techaholic.recruited.CRUD.Entite.JobOffer;
import techaholic.recruited.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceJobOffer implements IService<JobOffer> {
	private Connection connection = DataSource.getInstance().getConnection();
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public ServiceJobOffer() {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			Logger.getLogger(ServiceJobOffer.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void create(JobOffer jobOffer) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"INSERT INTO `job_offer`( `position_title`, `available_positions`, `location`, `tags`, `deadline`, `duration`, `description`) VALUES (?,?,?,?,?,?,?);");
		preparedStatement.setString(1, jobOffer.getPositionTitle());
		preparedStatement.setInt(2, jobOffer.getAvailablePositions());
		preparedStatement.setString(3, jobOffer.getLocation());
		preparedStatement.setString(4, jobOffer.getTags());
		preparedStatement.setDate(5, jobOffer.getDeadline());
		preparedStatement.setInt(6, jobOffer.getDuration());
		preparedStatement.setString(7, jobOffer.getDescription());

		preparedStatement.executeUpdate();
		System.out.println("jobOffer added");
	}

	@Override
	public void update(JobOffer jobOffer, int id) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"UPDATE `job_offer` SET `position_title`=?,`available_positions`=?,`location`=?,`tags`=?,`deadline`=?,`duration`=?,`description`=? WHERE id="
						+ id);

		preparedStatement.setString(1, jobOffer.getPositionTitle());
		preparedStatement.setInt(2, jobOffer.getAvailablePositions());
		preparedStatement.setString(3, jobOffer.getLocation());
		preparedStatement.setString(4, jobOffer.getTags());
		preparedStatement.setDate(5, jobOffer.getDeadline());
		preparedStatement.setInt(6, jobOffer.getDuration());
		preparedStatement.setString(7, jobOffer.getDescription());

		preparedStatement.executeUpdate();
		System.out.println("jobOffer updated");
	}

	@Override
	public void delete(int id) throws SQLException {
		preparedStatement = connection.prepareStatement("DELETE FROM `job_offer` WHERE `id` =?");
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		System.out.println("job offer deleted");
	}

	@Override
	public JobOffer findById(int id) throws SQLException {
		resultSet = statement.executeQuery("SELECT * FROM `job_offer` where `id`=" + id);
		if (resultSet.next()) {
			return new JobOffer(resultSet.getInt("id"),
					resultSet.getString(2),
					resultSet.getInt(3),
					resultSet.getString(4),
					resultSet.getString(5),
					resultSet.getDate(6),
					resultSet.getInt(7),
					resultSet.getString(8));
		} else
			return null;
	}

	@Override
	public ArrayList<JobOffer> getAll() throws SQLException {
		resultSet = statement.executeQuery("SELECT * FROM `job_offer`");
		ArrayList<JobOffer> jobOffers = new ArrayList<>();
		while (resultSet.next()) {
			jobOffers.add(new JobOffer(resultSet.getInt("id"),
					resultSet.getString(2),
					resultSet.getInt(3),
					resultSet.getString(4),
					resultSet.getString(5),
					resultSet.getDate(6),
					resultSet.getInt(7),
					resultSet.getString(8)));
		}
		return jobOffers;

	}

}

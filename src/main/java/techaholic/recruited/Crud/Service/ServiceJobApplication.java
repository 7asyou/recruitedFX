package techaholic.recruited.CRUD.Service;

import techaholic.recruited.App;
import techaholic.recruited.CRUD.Entite.JobApplication;
import techaholic.recruited.CRUD.Entite.JobOffer;
import techaholic.recruited.CRUD.Entite.User;
// import javafx.concurrent.Service;
import techaholic.recruited.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceJobApplication implements IService<JobApplication> {
	private Connection connection = DataSource.getInstance().getCon();
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private ServiceUser serviceUser;
	private ServiceJobOffer serviceJobOffer;

	public ServiceJobApplication() {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			Logger.getLogger(ServiceJobApplication.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void create(JobApplication jobApplication) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"INSERT INTO `job_application`( `status`, `descrioption`, `add-joints`, `date`, `evaluation`, `user_id`, `offre_id`) VALUES (?,?,?,?,?,?,?);");
		preparedStatement.setString(1, jobApplication.getStatus());
		preparedStatement.setString(2, jobApplication.getDescription());
		preparedStatement.setString(3, jobApplication.getAddJoints());
		preparedStatement.setTimestamp(4, jobApplication.getDate());
		preparedStatement.setInt(5, jobApplication.getEvaluation());
		preparedStatement.setInt(6, jobApplication.getUser().getId());
		preparedStatement.setInt(7, jobApplication.getJobOffer().getId());

		preparedStatement.executeUpdate();
		System.out.println("jobApplication added");
	}

	@Override
	public void update(JobApplication jobApplication, int id) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"UPDATE `job_application` SET `status`=?,`description`=?,`add-joints`=?,`date`=?,`evaluation`=?, `user_id`=?, `offre_id`=? WHERE id="
						+
						id);
		preparedStatement.setString(1, jobApplication.getStatus());
		preparedStatement.setString(2, jobApplication.getDescription());
		preparedStatement.setString(3, jobApplication.getAddJoints());
		preparedStatement.setTimestamp(4, jobApplication.getDate());
		preparedStatement.setInt(5, jobApplication.getEvaluation());
		preparedStatement.setInt(6, jobApplication.getUser().getId());
		preparedStatement.setInt(7, jobApplication.getJobOffer().getId());
		preparedStatement.executeUpdate();
		System.out.println("jobApplication updated");
	}

	@Override
	public void delete(int id) throws SQLException {
		preparedStatement = connection.prepareStatement("DELETE FROM job_application WHERE id=" + id);
		preparedStatement.executeUpdate();
		System.out.println("jobApplication deleted");
	}

	@Override
	public JobApplication findById(int id) throws SQLException {
		resultSet = statement.executeQuery("SELECT * FROM `job_application` WHERE id=" + id);
		if (resultSet.next()) {
			int user = resultSet.getInt(7);
			return new JobApplication(id, resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
					resultSet.getTimestamp(5), resultSet.getInt(6), serviceUser.findById(user),
					serviceJobOffer.findById(resultSet.getInt(8)));
		} else
			return null;
	}

	public ArrayList<JobApplication> getAll() throws SQLException {
		ArrayList<JobApplication> jobApplications = new ArrayList<>();
		resultSet = statement.executeQuery("SELECT * FROM `job_application`");

		ServiceUser serviceUser = ServiceUser.getInstance();
		ServiceJobOffer serviceJobOffer = new ServiceJobOffer();
		while (resultSet.next()) {
			jobApplications.add(
					new JobApplication(resultSet.getInt("id"),
							resultSet.getString(2),
							resultSet.getString(3),
							resultSet.getString(4),
							resultSet.getTimestamp(5),
							resultSet.getInt(6),
							serviceUser.findById(resultSet.getInt(7)),
							serviceJobOffer.findById(resultSet.getInt(8))));
		}
		return jobApplications;
	}

	public ArrayList<JobApplication> getByUser() throws SQLException {
		ArrayList<JobApplication> jobApplications = new ArrayList<>();
		resultSet = statement
				.executeQuery("SELECT * FROM `job_application` where user_id=" + String.valueOf(App.user.getId()));

		ServiceJobOffer serviceJobOffer = new ServiceJobOffer();

		while (resultSet.next()) {
			JobOffer jobOffer = serviceJobOffer.findById(resultSet.getInt(7));
			jobApplications.add(new JobApplication(resultSet.getInt("id"),
					resultSet.getString(2),
					resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5),
					resultSet.getInt(6), App.user, jobOffer));
		}
		return jobApplications;
	}

}

package techaholic.recruited.CRUD.Service;

import techaholic.recruited.CRUD.Entite.User;
import techaholic.recruited.Utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.valueOf;

public class ServiceUser implements IService<User> {

	private Connection connection = DataSource.getInstance().getCon();
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	
	private ServiceUser() {
		try {
			statement = connection.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	private static final ServiceUser instance = new ServiceUser();
	
	public static ServiceUser getInstance(){
		return instance;
	}

	@Override
	public void create(User user) throws SQLException {
		try {

			String req = "INSERT INTO"
						+" `user`"
						+" (`First_Name`, `Last_Name`, `role`, `phone_number`, `email`, `password_hash`)"
						+" VALUES (?,?,?,?,?,?);";
			preparedStatement = connection.prepareStatement(req);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, valueOf(user.getRole()));
			preparedStatement.setString(4, valueOf(user.getPhoneNumber()));
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getPasswordHash());

			preparedStatement.executeUpdate();

			System.out.println("User Created");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	@Override
	public void update(User user, int id) throws SQLException {
		try {

			String request = "UPDATE"
							+" `user`"
							+" SET `First_Name`=?,`Last_Name`=?,`role`=?,`phone_number`=?,`email`=?,`password_hash`=?"
							+" WHERE `id`=?";
					
			preparedStatement = connection.prepareStatement(request);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, valueOf(user.getRole()));
			preparedStatement.setString(4, valueOf(user.getPhoneNumber()));
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getPasswordHash());
			preparedStatement.setInt(7, id);

			preparedStatement.executeUpdate();
			System.out.println("User Updated");

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	@Override
	public void delete(int id) throws SQLException {
		String request = "DELETE"+
						" FROM `user`"+
						" WHERE `id` = ?";

		preparedStatement = connection.prepareStatement(request);
		preparedStatement.setInt(1, id);

		preparedStatement.executeUpdate();
		System.out.println("User Deleted");
	}

	public User findById(int id) throws SQLException {
		String request = "SELECT"+
						" * "+
						"FROM `user` "+
						"WHERE `id` = ?";

		preparedStatement=connection.prepareStatement(request);
		preparedStatement.setInt(1, id);
		
		resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next())
			return new User(resultSet.getInt(1), 
							resultSet.getString(2), 
							resultSet.getString(3), 
							resultSet.getInt(4), 
							resultSet.getInt(5),
							resultSet.getString(6), 
							resultSet.getString(7)
						);
		else
			return null;
	}

	public User findByCredential(String email, String passwordHash) throws SQLException {
		String request = "SELECT "
		+"* FROM `user` u"
		+" WHERE `email` = ? AND `password_hash` = ?";
		 
		preparedStatement = connection.prepareStatement(request);
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, passwordHash);
		
		resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next())
			return new User(resultSet.getInt(1), 
							resultSet.getString(2), 
							resultSet.getString(3), 
							resultSet.getInt(4), 
							resultSet.getInt(5),
							resultSet.getString(6), 
							resultSet.getString(7)
							);
		else
			return null;
	}

	@Override
	public ArrayList<User> getAll() throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		String request = "select * from `user`";
		preparedStatement=connection.prepareStatement(request);
		resultSet = preparedStatement.executeQuery(request);
		while (resultSet.next()) 
			users.add(new User(resultSet.getInt("id"), 
							resultSet.getString(2), 
							resultSet.getString(3), 
							resultSet.getInt(4), 
							resultSet.getInt(5),
							resultSet.getString(6), 
							resultSet.getString(7)
							)
					);
		
		return users;
	}
}

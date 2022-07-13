package techaholic.recruited.Crud.Service;

import techaholic.recruited.Crud.Utils.DataSource;

import techaholic.recruited.Crud.Entite.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.valueOf;

public class ServiceUser implements IService<User> {

	private Connection con = DataSource.getInstance().getCon();
	private Statement ste;
	private PreparedStatement pst;
	private ResultSet rs;

	public ServiceUser() {
		try {
			ste = con.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void create(User user) throws SQLException {
		try {

			String req = "INSERT INTO `user` (`First_Name`, `Last_Name`, `role`, `phone_number`, `email`, `password_hash`)"
					+
					" VALUES (?,?,?,?,?,?);";
			pst = con.prepareStatement(req);
			pst.setString(1, user.getFirstName());
			pst.setString(2, user.getLastName());
			pst.setString(3, valueOf(user.getRole()));
			pst.setString(4, valueOf(user.getPhoneNumber()));
			pst.setString(5, user.getEmail());
			pst.setInt(6, user.getPasswordHash());

			pst.executeUpdate();

			System.out.println("user added");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	@Override
	public void update(User user, int id) throws SQLException {
		try {

			String request = "UPDATE `user` SET `First_Name`=?,`Last_Name`=?,`role`=?,`phone_number`=?,`email`=?,`password_hash`=? WHERE `id`="
					+ id;
			pst = con.prepareStatement(request);
			pst.setString(1, user.getFirstName());
			pst.setString(2, user.getLastName());
			pst.setString(3, valueOf(user.getRole()));
			pst.setString(4, valueOf(user.getPhoneNumber()));
			pst.setString(5, user.getEmail());
			pst.setInt(6, user.getPasswordHash());

			pst.executeUpdate();

			System.out.println("user Updated");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	@Override
	public void delete(int id) throws SQLException {
		String request = "DELETE FROM `user` WHERE `id` = ?";
		pst = con.prepareStatement(request);
		pst.setInt(1, id);

		pst.executeUpdate();
		System.out.println("user deleted");
	}

	public User findById(int id) throws SQLException {
		String request = "SELECT * FROM `user` WHERE `id` = " + id;
		rs = ste.executeQuery(request);
		if (rs.next())
			return new User(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
					rs.getString(6), rs.getInt(7));

		else
			return null;
	}

	public User findByMail(String emailadres) throws SQLException {
		String request = "SELECT * FROM `user` WHERE `email` = '" + emailadres + "\'";
		rs = ste.executeQuery(request);
		if (rs.next())
			return new User(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
					rs.getString(6), rs.getInt(7));

		else
			return null;
	}

	@Override
	public ArrayList<User> getAll() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		String request = "select * from `user`";
		rs = ste.executeQuery(request);
		while (rs.next()) {
			list.add(new User(rs.getInt("id"), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
					rs.getString(6), rs.getInt(7)));
		}
		return list;
	}
}

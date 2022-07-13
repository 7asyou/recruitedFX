package techaholic.recruited.Crud.Service;

import techaholic.recruited.Crud.Utils.DataSource;

import techaholic.recruited.Crud.Entite.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceArticle implements IService<Article> {
	private Connection connection = DataSource.getInstance().getCon();
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public ServiceArticle() {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			Logger.getLogger(ServiceArticle.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@Override
	public void create(Article article) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"INSERT INTO `article`(  `category`,`title`, `description`, `image`) VALUES (?,?,?,?);");
		preparedStatement.setString(1, article.getCategory());
		preparedStatement.setString(2, article.getTitle());
		preparedStatement.setString(3, article.getDescription());
		preparedStatement.setString(4, article.getImage());

		preparedStatement.executeUpdate();
		System.out.println("Article added");
	}

	@Override
	public void update(Article Article, int id) throws SQLException {
		preparedStatement = connection.prepareStatement(
				"UPDATE `article` SET `category`=?,`title`=?,`description`=?,`image`=? WHERE id="
						+ id);

		preparedStatement.setString(1, Article.getCategory());
		preparedStatement.setString(2, Article.getTitle());
		preparedStatement.setString(3, Article.getDescription());
		preparedStatement.setString(4, Article.getImage());

		preparedStatement.executeUpdate();
		System.out.println("Article updated");
	}

	@Override
	public void delete(int id) throws SQLException {
		preparedStatement = connection.prepareStatement("DELETE FROM `article` WHERE `id` =?");
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		System.out.println("article deleted");
	}

	@Override
	public Article findById(int id) throws SQLException {
		resultSet = statement.executeQuery("SELECT * FROM `article` where `id`=" + id);
		if (resultSet.next()) {
			return new Article(resultSet.getInt("id"),
					resultSet.getString(2),
					resultSet.getString(3),
					resultSet.getString(4),
					resultSet.getString(5));
		} else
			return null;
	}

	@Override
	public ArrayList<Article> getAll() throws SQLException {
		resultSet = statement.executeQuery("SELECT * FROM `article`");
		ArrayList<Article> Articles = new ArrayList<>();
		while (resultSet.next()) {
			Articles.add(new Article(resultSet.getInt("id"),
					resultSet.getString(2),
					resultSet.getString(3),
					resultSet.getString(4),
					resultSet.getString(5)));
		}
		return Articles;

	}

}

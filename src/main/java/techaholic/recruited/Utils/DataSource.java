/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techaholic.recruited.Utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanabenfadhel
 */
public class DataSource {

	private static DataSource instance;

	private Connection connection;

	private String url = "jdbc:mysql://localhost:3306/recruited";

	private String login = "root";
	private String password = "";

	private DataSource() {

		try {
			System.out.println("Connecting to Database...");
			connection = DriverManager.getConnection(url, login, password);
			System.out.println("Connection established.");
		} catch (SQLException ex) {
			System.err.println(ex);
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DataSource getInstance() {
		if (instance == null) {
			instance = new DataSource();
		}
		return instance;
	}

}

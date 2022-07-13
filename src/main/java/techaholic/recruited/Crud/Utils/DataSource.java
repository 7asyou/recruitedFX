/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techaholic.recruited.Crud.Utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanabenfadhel
 */
public class DataSource {

	private static DataSource instance;

	private Connection con;

	private String url = "jdbc:mysql://localhost:3306/recruited";

	private String login = "root";
	private String pwd = "";

	private DataSource() {

		try {
			con = DriverManager.getConnection(url, login, pwd);
			System.out.println("Connexion établie");
		} catch (SQLException ex) {
			System.err.println(ex);
		}
	}

	public Connection getCon() {
		return con;
	}

	public static DataSource getInstance() {
		if (instance == null) {
			instance = new DataSource();
		}
		return instance;
	}

}

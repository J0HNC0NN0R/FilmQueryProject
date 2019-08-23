package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private final String userName = "student";
	private final String password = "student";

	static {
		try {
			Class.forName("com.mysq.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Actor findActorById(int actorID) {
		Actor actor = null;
		//ask instructor: "do we have to includ the connection/driver manager for this section?
		try {
			Connection conn = DriverManager.getConnection(URL, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ResultSet findActorsByFilmId(){
		
		
		return null;
	}
	

}

/*
 * All JDBC code will be in methods of the DatabaseAccessorObject class.

Implement findActorsByFilmId with an appropriate List implementation that will 
be populated using a ResultSet and returned.
Make sure your JDBC code uses PreparedStatement with bind variables instead of 
concatenating values into SQL strings.

*/
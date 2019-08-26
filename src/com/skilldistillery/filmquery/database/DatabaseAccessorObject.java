package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	// FIELDS
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String userName = "student";
	private final String password = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	// METHODS

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			String sql = "SELECT film.id, title, description," + "release_year, language_id, rental_duration,"
					+ "rental_rate, length, replacement_cost, rating, special_features, language.name" + " FROM film"
					+ " JOIN language ON film.language_id = language.id" + " WHERE film.id = ?";

			// Opening connection to database
			Connection conn = DriverManager.getConnection(URL, userName, password);
			// preparing statement
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("film.id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				// int rentalDuration = rs.getInt("rental_duration");
				// double rentalRate = rs.getDouble("rental_rate");
				// int length = rs.getInt("length");
				// double replacement = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				// String specialFeatures = rs.getString("special_features");
				String language = rs.getString("name");

				List<Actor> actors = findActorsByFilmId(filmId);

				film = new Film(id, title, description, releaseYear, languageId, rating, language, actors);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return film;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public List<Film> findFilmByKeyWord(String keyWord) {
		List<Film> films = new ArrayList<Film>();
		Film film = null;
		try {

			String sql = "SELECT film.id, title, description," + "release_year, language_id, rental_duration,"
					+ "rental_rate, length, replacement_cost, rating, special_features, language.name" + " FROM film"
					+ " JOIN language ON film.language_id = language.id" + " WHERE title LIKE ? OR description LIKE ?";

			// Opening connection to database
			Connection conn = DriverManager.getConnection(URL, userName, password);
			// preparing statement
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ("%" + keyWord + "%"));
			stmt.setString(2, ("%" + keyWord + "%"));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				int releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				// int rentalDuration = rs.getInt("rental_duration");
				// double rentalRate = rs.getDouble("rental_rate");
				// int length = rs.getInt("length");
				// double replacement = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				// String specialFeatures = rs.getString("special_features");
				String language = rs.getString("name");

				List<Actor> actors = findActorsByFilmId(id);

				film = new Film(id, title, description, releaseYear, languageId, rating, language, actors);
				films.add(film);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		if (films.size() == 0) {
			System.out.println("No results found");

		}
		return films;

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try {

			String sql = "SELECT film.id, film.title, film.description, film.release_year, film.language_id, "
					+ "film.rating " + "FROM film " + "WHERE title like ? " + "OR description like ? ";

			Connection conn = DriverManager.getConnection(URL, userName, password);

			// preparing statement
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				actor = new Actor(id, firstName, lastName);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return actor;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<Actor>();
		String sql = "SELECT a.id, a.first_name, a.last_name FROM actor a "
				+ "JOIN film_actor fa ON fa.actor_id = a.id " + "JOIN film f ON f.id = fa.film_id         "
				+ "WHERE f.id = ?";
		try {
			// Opening connection to database
			Connection conn = DriverManager.getConnection(URL, userName, password);

			// preparing statement for database, set variable to question mark
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor a = new Actor(id, firstName, lastName);
				actors.add(a);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return actors;

	}

	// SAME AS ^^ But the String Version

	@Override
	public List<Actor> findActorsByFilmId(String filmId) {
		List<Actor> actors = new ArrayList<Actor>();
		String sql = "SELECT a.id, a.first_name, a.last_name FROM actor a "
				+ "JOIN film_actor fa ON fa.actor_id = a.id " + "JOIN film f ON f.id = fa.film_id         "
				+ "WHERE f.id = ?";
		try {
			// Opening connection to database
			Connection conn = DriverManager.getConnection(URL, userName, password);

			// preparing statement for database, set variable to question mark
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, filmId);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				Actor a = new Actor(id, firstName, lastName);
				actors.add(a);

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return actors;
	}

}
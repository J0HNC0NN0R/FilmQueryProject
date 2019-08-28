package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;
import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	// FIELDS

	DatabaseAccessor db = new DatabaseAccessorObject();

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	// MAIN

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	// METHODS

//	private void test() {
//		Film film = db.findFilmById(2);
//		System.out.println(film);
//		Actor actor = db.findActorById(5);
//		System.out.println(actor);
//
//	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void startUserInterface(Scanner input) {

		String choice = "";
		while (choice != "3") {
			try {
				System.out.println();
				System.out.println("FILM QUERY PRO APPLICATION\n");
				System.out.println("Enter \"1\" to look up a movie by its ID#");
				System.out.println("Enter \"2\" to look up movie by a keyword");
				System.out.println("Enter \"3\" to exit the application.");
				choice = input.next();
				switch (choice) {
				case "1":
					System.out.println("Please enter a movie's ID# to look it up: ");
					int movieChoice = input.nextInt();
					if (db.findFilmById(movieChoice) != null) {
						System.out.println(db.findFilmById(movieChoice));
					} else {

						System.out.println("That movie is not in the collection...");
					}
					break;

				case "2":
					System.out.print("Please enter a keyword to look up a movie: ");
					String keyWord = input.next();
					List<Film> listOfFilms = db.findFilmByKeyWord(keyWord);

					for (Film film : listOfFilms) {
						System.out.println(film);

					}
					break;

				case "3":
					System.out.println("Exiting application");
					System.exit(0);
					break;

				default:
					System.out.println("Please select a number between 1 and 3");

				}

			} catch (Exception e) {
				System.out.println("Invalid option.");
			}
		}
	}

}
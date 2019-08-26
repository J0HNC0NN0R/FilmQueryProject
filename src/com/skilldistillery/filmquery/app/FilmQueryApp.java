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
		System.out.println("FILM QUERY PRO APPLICATION\n");
		System.out.println("Enter \"1\" to look up a movie by its ID#");
		System.out.println("Enter \"2\" to look up movie by a keyword");
		System.out.println("Enter \"3\" to exit the application.");

		try {
			String choice = input.next();
			switch (choice) {
			case "1":
				System.out.println("Please enter a movie's ID# to look it up: ");
				int movieChoice = input.nextInt();
				if (movieChoice < 1 || movieChoice > 1000) {
					System.out.println("That movie is not in the collection...");
				} else {
					System.out.println(db.findFilmById(movieChoice));

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

			}

			if (choice != "3") {
				input.nextLine();
				//again(input);
				startUserInterface(input);
			}

		} catch (Exception e) {
			System.out.println("Invalid option.");
			startUserInterface(input);
		}

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void again(Scanner input) {
		System.out.println();
		System.out.println("Would you like to use the Film Query Application Again? (Y/N)");

		String choice = input.next().toUpperCase();
		switch (choice) {
		case "Y":
			startUserInterface(input);
			break;

		case "N":
			System.out.println("Exiting application");
			break;

		default:
			System.out.println("Please enter either \"Y\" or \"N");
			again(input);

		}

	}
}
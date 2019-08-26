package com.skilldistillery.filmquery.entities;

public class Actor {

	// FIELDS

	private int id;
	private String firstName;
	private String lastName;

//////////////////////////////////////////////////////////////////////////////////////////////

	// CONSTRUCTORS

	public Actor(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;

	}

//////////////////////////////////////////////////////////////////////////////////////////////

	// GETTERS AND SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

//////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Actor ID: ");
		builder.append(id);
		builder.append("\nFirst Name: ");
		builder.append(firstName);
		builder.append("\nLast Name: ");
		builder.append(lastName);
		return builder.toString();
	}

}

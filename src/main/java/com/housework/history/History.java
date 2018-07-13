package com.housework.history;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "history")
public class History {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "type")
	@NotNull
	@NotBlank
	private String type;

	@Column(name = "name")
	@NotNull
	@NotBlank
	private String name;

	@Column(name = "personId")
	private int personId;

	@Column(name = "points")
	private int points;

	@Column(name = "date")
	@NotNull
	@NotBlank
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public History() {
		super();
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public History(int id, String type, String name, int personId, int points, String date) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.personId = personId;
		this.points = points;
		this.date = date;
	}

}

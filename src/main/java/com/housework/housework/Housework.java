package com.housework.housework;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name ="housework")
public class Housework {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
    @NotNull
    @NotBlank
	private String name;
	
	@Column(name = "points")
    @NotNull
    @NotBlank
	private int points;
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
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Housework(int id, String name, int points) {
		super();
		this.id = id;
		this.name = name;
		this.points = points;
	}
	public Housework() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

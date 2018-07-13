package com.housework.housework;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class HouseworkSelect {

	@NotEmpty
	@NotNull
	private List<Integer> select;

	// user id
	@NotNull
	private int id;

	public HouseworkSelect(List<Integer> select, int id) {
		super();
		this.select = select;
		this.id = id;
	}

	public HouseworkSelect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Integer> getSelect() {
		return select;
	}

	public void setSelect(List<Integer> select) {
		this.select = select;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}

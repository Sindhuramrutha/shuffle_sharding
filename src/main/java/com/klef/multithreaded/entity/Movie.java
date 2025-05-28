package com.klef.multithreaded.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Movie_table")
@AllArgsConstructor
@NoArgsConstructor 
public class Movie 
{
	@Id
	@GeneratedValue
	private int id;
	private String Name;
	private int YearOfRelease;
	private String Cast;
	private String Director;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getYearOfRelease() {
		return YearOfRelease;
	}
	public void setYearOfRelease(int yearOfRelease) {
		YearOfRelease = yearOfRelease;
	}
	public String getCast() {
		return Cast;
	}
	public void setCast(String cast) {
		Cast = cast;
	}
	public String getDirector() {
		return Director;
	}
	public void setDirector(String director) {
		Director = director;
	}
	
	
	

}

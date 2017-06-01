package pl.edu.agh.edeansoffice.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fields_of_study")
public class FieldsOfStudy {

	private String name;
	private String department;
	private String level;
	private String profil;
	private String formOfStudy;
	private String speciality;
	private int numberOfSemesters;
	private int ects;
	private int totalNumberOfHours;
	private Date dateCreate;
	private int id;
	
	public FieldsOfStudy(String name, String department, String level, String profil, String formOfStudy, String speciality, 
			int numberOfSemesters, int ects, int totalNumberOfHours) {
		
		this.name = name;
		this.department = department;
		this.level = level;
		this.profil = profil;
		this.formOfStudy = formOfStudy;
		this.speciality = speciality;
		this.numberOfSemesters = numberOfSemesters;
		this.ects = ects;
		this.totalNumberOfHours = totalNumberOfHours;
		dateCreate = new Date();
	}
	
	public FieldsOfStudy() {
		
	}

	@NotNull
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Column(name = "department")
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@NotNull
	@Column(name = "level")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@NotNull
	@Column(name = "profil")
	public String getProfil() {
		return profil;
	}

	public void setProfil(String profil) {
		this.profil = profil;
	}

	@NotNull
	@Column(name = "form_of_study")
	public String getFormOfStudy() {
		return formOfStudy;
	}

	public void setFormOfStudy(String formOfStudy) {
		this.formOfStudy = formOfStudy;
	}

	@NotNull
	@Column(name = "speciality")
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@NotNull
	@Column(name = "number_of_semesters")
	public int getNumberOfSemesters() {
		return numberOfSemesters;
	}

	public void setNumberOfSemesters(int numberOfSemesters) {
		this.numberOfSemesters = numberOfSemesters;
	}

	@NotNull
	@Column(name = "ects")
	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}

	@NotNull
	@Column(name = "total_number_of_hours")
	public int getTotalNumberOfHours() {
		return totalNumberOfHours;
	}

	public void setTotalNumberOfHours(int totalNumberOfHours) {
		this.totalNumberOfHours = totalNumberOfHours;
	}

	@NotNull
	@Column(name = "date_create")
	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "id", unique = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}

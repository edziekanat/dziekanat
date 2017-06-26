package pl.edu.agh.edeansoffice.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "students")
public class Student implements EMailGenerator {

	private String name;
	private String surname;
	private String studentsBookNumber;
	private String eMail;
	private String city;
	private String phone;
	private String password;
	private String fieldOfStudy;
	private int yearOfStudy;
	private int currentSemester;
	private Date dateCreate;
	private int id;
	
	public Student(String name, String surname, String city, String phone,
			String fieldOfStudy, int yearOfStudy, int currentSemester) {
		
		this.name = name;
		this.surname = surname;
		studentsBookNumber = RandomStringUtils.randomNumeric(6, 6);
		eMail = createEmailAccount(name, surname);
		this.city = city;
		this.phone = phone;
		password = RandomStringUtils.randomAlphabetic(6) + RandomUtils.nextInt(0, 300);
		this.fieldOfStudy = fieldOfStudy;
		this.yearOfStudy = yearOfStudy;
		this.currentSemester = currentSemester;
		dateCreate = new Date();
	}
	
	public Student() {
		
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
	@Column(name = "surname")
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@NotNull
	@Column(name = "students_book_number")
	public String getStudentsBookNumber() {
		return studentsBookNumber;
	}

	public void setStudentsBookNumber(String studentsBookNumber) {
		this.studentsBookNumber = studentsBookNumber;
	}

	@NotNull
	@Column(name = "e_mail")
	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@NotNull
	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@NotNull
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotNull
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	@Column(name = "field_of_study")
	public String getFieldOfStudy() {
		return fieldOfStudy;
	}

	public void setFieldOfStudy(String fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	@NotNull
	@Column(name = "year_of_study")
	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	
	@NotNull
	@Column(name = "current_semester")
	public int getCurrentSemester() {
		return currentSemester;
	}
	
	public void setCurrentSemester(int currentSemester) {
		this.currentSemester = currentSemester;
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

	@Override
	public String createEmailAccount(String name, String surname) {
		
		String firstPart = StringUtils.stripAccents(name).toLowerCase().substring(0, 3);
		String secondPart = StringUtils.stripAccents(surname).toLowerCase().substring(0, 2);
		
		return firstPart + secondPart + "@student.uniwersytet.edu.pl";
	}
	
	
	
	
	
}

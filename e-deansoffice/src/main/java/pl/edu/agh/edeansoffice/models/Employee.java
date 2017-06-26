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
@Table(name = "employees")
public class Employee implements EMailGenerator {

	private int id;
	private String name;
	private String surname;
	private String eMail;
	private String password;
	private String city;
	private String phone;
	private Date dateCreate;

	public Employee(String name, String surname, String city, String phone) {
		this.name = name;
		this.surname = surname;
		eMail = createEmailAccount(name, surname);
		password = RandomStringUtils.randomAlphabetic(6) + RandomUtils.nextInt(0, 300);
		this.city = city;
		this.phone = phone;
		dateCreate = new Date();
	}

	public Employee() {

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
	@Column(name = "e_mail")
	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
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
	@Column(name = "date_create")
	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Id // primary key
	@GeneratedValue // hibernate will create a value for us
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

		return firstPart + secondPart + "@administracja.uniwersytet.edu.pl";
	}

}

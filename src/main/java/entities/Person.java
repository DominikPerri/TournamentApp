package entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import enums.Gender;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private long id;
	
	private String firstName;
	
	private String lastName;
	
	private Gender gender;
	
	private String phoneNumber;
	
	@Column(unique=true)
	private String mailAddress;
	
	private String streetName;
	private long houseNumber;
	private long zipCode;
	private String cityName;
	
	@OneToMany(mappedBy = "person")
    private List<TeamCoach> coachOf;
	
	@OneToMany(mappedBy = "person")
    private List<TournamentRegistration> registered;

	public Person(){
		coachOf = new ArrayList<TeamCoach>();
		registered = new ArrayList<TournamentRegistration>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public long getNumber() {
		return houseNumber;
	}

	public void setNumber(long number) {
		this.houseNumber = number;
	}

	public long getZipCode() {
		return zipCode;
	}

	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<TeamCoach> getCoachOf() {
		return coachOf;
	}

	public void setCoachOf(List<TeamCoach> coachOf) {
		this.coachOf = coachOf;
	}

	public List<TournamentRegistration> getRegistered() {
		return registered;
	}

	public void setRegistered(List<TournamentRegistration> registered) {
		this.registered = registered;
	}
	

}

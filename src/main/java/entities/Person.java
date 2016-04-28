package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import enums.Gender;

@Entity
@NamedQueries({
		@NamedQuery(name = Person.findPersonByMail, 
				query = "SELECT p FROM Person p WHERE p.mailAddress = :mailAddress") })
public class Person extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public final static String findPersonByMail = "findPersonByMail";

	private String firstName;

	private String lastName;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	private String phoneNumber;

	private Address address;

	@Column(unique = true)
	private String mailAddress;

	@OneToMany(mappedBy = "person", fetch=FetchType.LAZY)
	private List<TeamCoach> coachOf;

	@OneToMany(mappedBy = "person", fetch=FetchType.LAZY)
	private List<TournamentRegistration> registered;

	public Person() {
		coachOf = new ArrayList<>();
		registered = new ArrayList<>();
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

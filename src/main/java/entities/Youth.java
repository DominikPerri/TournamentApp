package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import enums.AgeLevel;
import enums.Gender;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "gender", "ageLevel" }))
@NamedQueries({
		@NamedQuery(name = "findYouth", 
				query = "from Youth y where y.gender = :gender and y.ageLevel = :ageLevel") })
public class Youth {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AgeLevel ageLevel;
	
	public Youth(AgeLevel age, Gender gender){
		this.ageLevel = age;
		this.gender = gender;
	}
	
	public Youth(){
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public AgeLevel getAgeLevel() {
		return ageLevel;
	}

	public void setAgeLevel(AgeLevel ageLevel) {
		this.ageLevel = ageLevel;
	}

	public String toString() {
		return ageLevel + "-" + gender;
	}

}

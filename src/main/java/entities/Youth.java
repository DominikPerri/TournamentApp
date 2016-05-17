package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import enums.AgeLevel;
import enums.Gender;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "gender", "ageLevel" }))
@NamedQueries({
		@NamedQuery(name = Youth.findYouth, query = "SELECT y FROM Youth y WHERE y.gender = :gender AND y.ageLevel = :ageLevel") })
public class Youth extends BaseEntity {

	public final static String findYouth = "findYouth";

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AgeLevel ageLevel;

	public Youth(AgeLevel age, Gender gender) {
		this.ageLevel = age;
		this.gender = gender;
	}

	public Youth() {
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

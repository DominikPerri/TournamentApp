package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = Club.findClubByName, query = "SELECT c FROM Club c WHERE c.name = :name") })
public class Club extends BaseEntity {

	public static final String findClubByName = "Club.findClubByName";

	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String name;

	public Club() {
	}

	public Club(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

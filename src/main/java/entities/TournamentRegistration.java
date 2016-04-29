package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tournament_registration")
public class TournamentRegistration extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "team_id")
	private Team team;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tournament_id")
	private Tournament tournament;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate;
	
	
	
	public TournamentRegistration(Person person, Team team, Tournament tournament) {
		this.person = person;
		this.team = team;
		this.tournament = tournament;
		this.registrationDate = new Date();
	}

	public TournamentRegistration(){
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

}

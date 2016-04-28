package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "youth_id", "club_id" }))
@NamedQueries({ 
	@NamedQuery(name = Team.findTeam,
			query = "SELECT t FROM Team t WHERE t.club.id = :club AND t.youth.id = :youth") })
public class Team extends BaseEntity {
	
	public static final String findTeam = "findTeam";

	private static final long serialVersionUID = 1L;

	@ManyToOne (fetch=FetchType.EAGER)
	private Youth youth;

	@ManyToOne (fetch=FetchType.EAGER)
	private Club club;

	@OneToMany(mappedBy = "team")
	private List<TeamCoach> coachedBy;

	@OneToMany(mappedBy = "team")
	private List<TournamentRegistration> registered;

	public Team(Club club, Youth youth) {
		this.club = club;
		this.youth = youth;
	}

	public Team() {
	}

	public Youth getYouth() {
		return youth;
	}

	public void setYouth(Youth youth) {
		this.youth = youth;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public List<TeamCoach> getCoachedBy() {
		return coachedBy;
	}

	public void setCoachedBy(List<TeamCoach> coachedBy) {
		this.coachedBy = coachedBy;
	}

	public List<TournamentRegistration> getRegistered() {
		return registered;
	}

	public void setRegistered(List<TournamentRegistration> registered) {
		this.registered = registered;
	}

}

package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "youth_id", "club_id" }))
@NamedQueries({
	@NamedQuery(name = "findTeam", 
			query = "from Team t where t.club.id = :club and t.youth.id = :youth") })
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private long id;
	
	@ManyToOne
	private Youth youth;
	
	@ManyToOne
	private Club club;
	
	@OneToMany(mappedBy = "team")
    private List<TeamCoach> coachedBy;
	
	@OneToMany(mappedBy = "team")
    private List<TournamentRegistration> registered;

	public Team(Club club, Youth youth){
		this.club = club;
		this.youth = youth;
	}
	
	public Team(){
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

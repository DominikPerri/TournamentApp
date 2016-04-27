package entities;

import java.util.Date;
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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "youth_id", "date" }))
@NamedQueries({
	@NamedQuery(name = "findTournament", 
			query = "from Tournament t where t.youth.id = :youth and t.date = :date") ,
	@NamedQuery(name = "findOpenTournament", 
			query = "from Tournament t where t.isOpen=1") })

public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	private Youth youth;

	private int maxNumber;

	private boolean isOpen;

	private Date date;

	@OneToMany(mappedBy = "tournament")
	private List<TournamentRegistration> registered;

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

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<TournamentRegistration> getRegistered() {
		return registered;
	}

	public void setRegistered(List<TournamentRegistration> registered) {
		this.registered = registered;
	}

}
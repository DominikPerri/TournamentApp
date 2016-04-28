package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "youth_id", "date" }))
@NamedQueries({
		@NamedQuery(name = Tournament.findTournament, 
				query = "SELECT t FROM Tournament t WHERE t.youth.id = :youth AND t.date = :date"),
		@NamedQuery(name = Tournament.findOpenTournaments, 
				query = "SELECT t FROM Tournament t WHERE t.isOpen = 1") })

public class Tournament extends BaseEntity {

	public static final String findTournament = "findTournament";
	public static final String findOpenTournaments = "findOpenTournament";

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.EAGER)
	private Youth youth;

	private int maxNumber;

	private boolean isOpen;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@OneToMany(mappedBy = "tournament", fetch=FetchType.LAZY)
	private List<TournamentRegistration> registered;

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
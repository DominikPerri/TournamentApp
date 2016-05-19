package persistence;

import javax.persistence.Query;

import java.util.List;

import entities.Tournament;

public class TournamentService extends BaseService {
	
	public TournamentService(){
		super();
	}

	/**
	 * Persist a new tournament in the database. If the tournament already exists then
	 * return this entity from the database
	 * 
	 * return all open tournaments
	 * 
	 * @param tournament
	 * @return persisted tournament
	 *
	 */
	public Tournament persist(Tournament tournament) {
		super.setup();
		super.openConnection();
		Query query = em.createNamedQuery(Tournament.findTournament);
		query.setParameter("date", tournament.getDate());
		query.setParameter("youth", tournament.getYouth().getId());
		
		@SuppressWarnings("unchecked")
		List<Tournament> tournamentList = query.getResultList();
		
		if (tournamentList.size()==0){
			super.persist(tournament);
		} else {
			tournament = tournamentList.get(0);
		}
		closeEntityManager();
		return tournament;
	}

	public List<Tournament> getOpenTournaments () {
		super.setup();
		super.openConnection();
		Query query = em.createNamedQuery(Tournament.findOpenTournaments);
		@SuppressWarnings("unchecked")
		List<Tournament> tournamentList = ((List<Tournament>)query.getResultList());
		closeEntityManager();
		return tournamentList;
	}
	
}

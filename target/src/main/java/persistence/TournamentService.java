package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;
import entities.Tournament;

public class TournamentService {

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
	Tournament tournament = new Tournament();
	
	public Tournament persist(Tournament tournament) {
		try {
			tournament = getExistingTounament(tournament);
		} catch (NoResultException e) {
			tournament = persistNewTournament(tournament);
		}
		return tournament;
	}
	
	private Tournament getExistingTounament(Tournament tournament) throws NoResultException{
		EntityManager em = EntityManagerService.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("findTournament");
		query.setParameter("date", tournament.getDate());
		query.setParameter("youth", tournament.getYouth().getId());
		tournament = (Tournament) query.getSingleResult();
		em.close();
		return tournament;

	}

	private Tournament persistNewTournament(Tournament tournament)  {
		EntityManager em = EntityManagerService.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(tournament);
		t.commit();
		em.close();
		return tournament;
	}

	public List<Tournament> getopenTournaments () {
		EntityManager em = EntityManagerService.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("findOpenTournament");
		@SuppressWarnings("unchecked")
		List<Tournament> tournament = ((List<Tournament>)query.getResultList());
		em.close();
		return tournament;
		
	}
	
}

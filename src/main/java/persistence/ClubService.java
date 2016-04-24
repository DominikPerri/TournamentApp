package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entities.Club;

/**
 * Service class to persist and get clubs from the database
 * 
 * @author Dominik
 *
 */
public class ClubService {

	public ClubService() {
	}

	/**
	 * Persist a new club in the database. If the club already exists then
	 * return this entity from the database
	 * 
	 * @param club
	 * @return persisted club
	 */
	public Club persist(Club club) {
		try{
			club = getClubByName(club);
		} catch (NoResultException e){
			club = persistNewClub(club);
		}
		return club;
	}

	private Club getClubByName(Club club) throws NoResultException{
		EntityManager em = EntityManagerService.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("findClubByName");
		query.setParameter("name", club.getName());
		club = (Club) query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return club;

	}

	private Club persistNewClub(Club club) {
		EntityManager em = EntityManagerService.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(club);
		t.commit();
		em.close();
		return club;
	}

}

package persistence;

import java.util.List;

import javax.persistence.Query;

import entities.Club;

/**
 * Service class to persist and get clubs from the database
 * 
 * @author Dominik
 *
 */
public class ClubService extends BaseService {

	public ClubService() {
		super();
	}

	/**
	 * Persist a new club in the database. If the club already exists then
	 * return this entity from the database
	 * 
	 * @param club
	 * @return persisted club
	 */
	public Club persist(Club club) {
		super.setup();
		super.openConnection();
		Query query = em.createNamedQuery(Club.findClubByName);
		query.setParameter("name", club.getName());

		@SuppressWarnings("unchecked")
		List<Club> clubs = query.getResultList();

		if (clubs.size() == 0) {
			super.persist(club);
		} else {
			club = clubs.get(0);
		}
		closeEntityManager();
		return club;
	}

}

package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entities.Team;


public class TeamService {

	/**
	 * Persist a new team in the database. If the team already exists then
	 * return this entity from the database
	 * 
	 * @param team
	 * @return persisted team
	 */
	public Team persist(Team team) {
		try {
			team = getExistingTeam(team);
		} catch (NoResultException e) {
			team = persistNewTeam(team);
		}
		return team;
	}

	private Team getExistingTeam(Team team) throws NoResultException{
		EntityManager em = EntityManagerService.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("findTeam");
		query.setParameter("club", team.getClub().getId());
		query.setParameter("youth", team.getYouth().getId());
		team = (Team) query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return team;

	}

	private Team persistNewTeam(Team team)  {
		EntityManager em = EntityManagerService.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(team);
		t.commit();
		em.close();
		return team;
	}

}

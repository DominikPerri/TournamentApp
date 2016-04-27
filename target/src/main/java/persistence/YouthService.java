package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import entities.Youth;

public class YouthService {

	/**
	 * Persist a new youth in the database. If the youth already exists then
	 * return this entity from the database
	 * 
	 * @param youth
	 * @return persisted youth
	 */
	public Youth persist(Youth youth) {
		try{
			youth = getExistingYouth(youth);
		} catch (NoResultException e){
			youth = persistNewYouth(youth);
		}
		return youth;
	}

	private Youth getExistingYouth(Youth youth) throws NoResultException{
		EntityManager em = EntityManagerService.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("findYouth");
		query.setParameter("gender", youth.getGender());
		query.setParameter("ageLevel", youth.getAgeLevel());
		youth = (Youth) query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return youth;
	}

	private Youth persistNewYouth(Youth youth)  {
		EntityManager em = EntityManagerService.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(youth);
		t.commit();
		em.close();
		return youth;
	}

}

package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.NoResultException;

import entities.Person;

public class PersonService {

	public PersonService() {
			}

	/**
	 * Persist a new person in the database. 
	 * return this entity from the database
	 * 
	 * @param person
	 * @return persisted person
	 */
	
	public Person persist(Person person) {
		 try   {
				person = getPersonbymail(person);
		 } catch (NoResultException e) {
			 person = persistNewPerson(person);
		 }
				return person;
	   }
	
	private Person getPersonbymail(Person person) throws NoResultException{
		EntityManager em = EntityManagerService.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("findPersonbymail");
		query.setParameter("mailAddress", person.getMailAddress());
		person = (Person) query.getSingleResult();
		em.close();
		return person;
	}

	private Person persistNewPerson(Person person){
		EntityManager em = EntityManagerService.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(person);
		t.commit();
		em.close();
		return person;
	}
	
}

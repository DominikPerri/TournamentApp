package persistence;

import java.util.List;

import javax.persistence.Query;

import entities.Person;

public class PersonService extends BaseService {

	public PersonService() {
		super();
	}

	/**
	 * Persist a new person in the database. return this entity from the
	 * database.
	 * 
	 * @param person
	 * @return persisted person
	 */

	public Person persist(Person person) {
		super.setup();
		super.openConnection();
		Query query = em.createNamedQuery(Person.findPersonByMail);
		query.setParameter("mailAddress", person.getMailAddress());

		@SuppressWarnings("unchecked")
		List<Person> personList = query.getResultList();

		if (personList.size() == 0) {
			super.persist(person);
		} else {
			person = personList.get(0);
		}
		closeEntityManager();
		return person;
	}

}

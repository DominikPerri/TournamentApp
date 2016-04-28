package persistence;

import java.util.List;
import javax.persistence.Query;

import entities.Youth;

public class YouthService extends BaseService {

	public YouthService() {
		super();
	}

	/**
	 * Persist a new youth in the database. If the youth already exists then
	 * return this entity from the database
	 * 
	 * @param youth
	 * @return persisted youth
	 */
	public Youth persist(Youth youth) {
		super.setup();
		super.openConnection();
		Query query = em.createNamedQuery(Youth.findYouth);
		query.setParameter("gender", youth.getGender());
		query.setParameter("ageLevel", youth.getAgeLevel());

		@SuppressWarnings("unchecked")
		List<Youth> youthList = query.getResultList();

		if (youthList.size() == 0) {
			super.persist(youth);
		} else {
			youth = youthList.get(0);
		}
		closeEntityManager();
		return youth;
	}

}

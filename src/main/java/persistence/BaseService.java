package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import entities.BaseEntity;

public class BaseService {

	private EntityManagerFactory emf;
	protected EntityManager em;

	public BaseService() {
	}

	public void setup() {
		emf = EntityManagerFactoryService.getEntityManagerFactory();
		em = emf.createEntityManager();
	}

	protected void persist(BaseEntity entity) {
		setup();
		openConnection();
		em.persist(entity);
		commitStatement();
		closeEntityManager();
	}
	
	public void delete(BaseEntity entity) {
		setup();
		openConnection();
		entity = em.merge(entity);
		em.remove(entity);
		commitStatement();
		closeEntityManager();
	}

	protected void openConnection() {
		if (em == null) {
			throw new IllegalStateException("EntityManager is not initilized. Is setup() called?");
		}
		if (em.getTransaction().isActive()) {
			throw new PersistenceException("Connection already active");
		}
		em.getTransaction().begin();
	}

	protected void commitStatement() {
		if (!em.isOpen()) {
			throw new PersistenceException("Connection is closed");
		} else if (!em.getTransaction().isActive()) {
			throw new PersistenceException("No Transaction is active");
		}
		em.getTransaction().commit();
	}

	protected void closeEntityManager() {
		if (em.isOpen()) {
			em.close();
		}
	}

	protected void shutDown() {
		if (emf.isOpen()) {
			emf.close();
		}
	}

}

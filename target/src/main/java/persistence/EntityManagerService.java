package persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerService {
	
	
	private static EntityManagerFactory emf;
	
	private EntityManagerService(){
		createEntityManagerFactory();
	}
	
	private static void createEntityManagerFactory(){
		emf = Persistence.createEntityManagerFactory("toureg");
	}
	
	public static EntityManager getEntityManager(){
		if (emf == null){
			createEntityManagerFactory();
		}
		return emf.createEntityManager();
	}
	
	public static void close(){
		emf.close();
	}
	

}

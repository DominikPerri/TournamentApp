package persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryService {

	private static EntityManagerFactory emf = null;

	private EntityManagerFactoryService() {
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {

			init();

		}
		return emf;
	}

	private static void init() {
		emf = Persistence.createEntityManagerFactory("toureg");
	}
	
	public static void close(){
		if (emf == null){
			return;
		} else if (emf.isOpen()){
			emf.close();
		}
	}

}

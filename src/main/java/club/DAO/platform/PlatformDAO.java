package club.DAO.platform;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class PlatformDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	public boolean saveToDB(Platform platform) {
			
		return entityManager.merge(platform) != null;
	}

	public Platform getFromDBById(int id) {
		return entityManager.find(Platform.class, id);
	}

}

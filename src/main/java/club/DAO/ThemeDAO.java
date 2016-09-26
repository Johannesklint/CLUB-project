package club.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ThemeDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public Theme getUserById(int id) {
		return entityManager.find(Theme.class, id);
	}

}

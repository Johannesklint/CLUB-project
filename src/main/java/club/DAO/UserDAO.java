package club.DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class UserDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	public boolean saveToDB(User user) {
		return entityManager.merge(user) != null;
	}
	
	// Skicka User till databas, hämta osv.

}

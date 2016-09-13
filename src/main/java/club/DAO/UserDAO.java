package club.DAO;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class UserDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	public boolean saveToDB(User user) {
		return entityManager.merge(user) != null;
	}

	public List<User> getAll() {
		Query findAll = entityManager.createNamedQuery("User.findAll");
		return (List<User>)findAll.getResultList();
	}

	public User getUserById(int selectedUserId) {
		return entityManager.find(User.class, selectedUserId);
	}
	
	// Skicka User till databas, hämta osv.

}

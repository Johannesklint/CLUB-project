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

	public boolean updateDB(User user) {		
//		user = entityManager.find(User.class, 1);
//		entityManager.getTransaction().begin();
//		user.setEmail("karl@karl.se");
//		entityManager.getTransaction().commit();
		entityManager.persist(user);
		return true;
		
	}

	public User getUserById(int id) {
		return entityManager.find(User.class, id);
	}
}

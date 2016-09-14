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

	public List<User> getAll() {
		Query findAll = entityManager.createNamedQuery("User.findAll");
		return (List<User>)findAll.getResultList();
	}
}

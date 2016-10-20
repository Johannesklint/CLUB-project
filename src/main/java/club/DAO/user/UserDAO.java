package club.DAO.user;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class UserDAO {
	
	@PersistenceContext
	EntityManager entityManager;

	public User saveToDB(User user) {
		return entityManager.merge(user);	
	}



	public User getUserById(int id) {
		return entityManager.find(User.class, id);
	}

	public User getUserByEmail(String email) {
		Query query = entityManager.createNamedQuery("User.findByEmail");

		query.setParameter("email", email);
		
		//TODO: detta är fel att använda exception för fel man faktiskt kan medvetet göra IMO. borde finnas något sätt att se om resultat finns innan man hämtar det.
		try {
			return (User)query.getSingleResult();		
		}
		catch(NoResultException e) {
			return null;
		}
	}
	
	// Skicka User till databas, hämta osv.

	public List<User> getAll() {
		Query findAll = entityManager.createNamedQuery("User.findAll");
		return (List<User>)findAll.getResultList();
	}

	public boolean deleteUser(int id) {
		User user = entityManager.find(User.class, id);
		if(user != null){
			 entityManager.remove(user);
			 return true;
		}
		return false;
	}



	public List<User> getUsersByEmail(String email) {
		Query findByEmail = entityManager.createNamedQuery("User.findByEmail");
		findByEmail.setParameter("email", email);
		return (List<User>)findByEmail.getResultList();
	}
}
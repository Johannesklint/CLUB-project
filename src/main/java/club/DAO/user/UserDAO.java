package club.DAO.user;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import club.DAO.GenericCrudDao;

@Stateful
public class UserDAO extends GenericCrudDao<User> {
	
	@PersistenceContext
	EntityManager entityManager;

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

	@SuppressWarnings("unchecked")
	public List<User> getUsersByEmail(String email) {
		Query findByEmail = entityManager.createNamedQuery("User.findByEmail");
		findByEmail.setParameter("email", email);
		return (List<User>)findByEmail.getResultList();
	}
}

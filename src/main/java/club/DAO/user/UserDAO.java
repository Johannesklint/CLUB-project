package club.DAO.user;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import club.DAO.GenericCrudDao;
import club.DAO.user.User.ApprovedState;

@Stateful
public class UserDAO extends GenericCrudDao<User> {
	
	@PersistenceContext
	EntityManager entityManager;

	public User getUserByEmail(String email) {
		
		Query query = entityManager.createNamedQuery("User.findByEmail");

		query.setParameter("email", email);
		
		try {
			return (User)query.getSingleResult();		
		}
		catch(NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllValidUsers() {
		Query query = entityManager.createNamedQuery("User.findAll");
		List<User> allUsers = (List<User>)query.getResultList();
		
		return allUsers
				.stream()
				.filter( user -> user.getApprovedState()==ApprovedState.GRANTED )
				.map(user -> (User) user) // cast to please the compiler
				.collect(Collectors.toList());

	
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByEmail(String email) {
		Query findByEmail = entityManager.createNamedQuery("User.findByEmail");
		findByEmail.setParameter("email", email);
		return (List<User>)findByEmail.getResultList();
	}

	public User getByCpcid(String cpcid) {
		Query query = entityManager.createNamedQuery("User.findByCpcid");

		query.setParameter("cpcid", cpcid);
		
		try {
			return (User)query.getSingleResult();		
		}
		catch(NoResultException e) {
			return null;
		}
	}
}

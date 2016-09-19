package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import club.DAO.User;
import club.DAO.User.ApprovedState;
import club.DAO.UserDAO;
import club.EJB.interfaces.LocalUser;

@Stateless
public class UserEJB implements LocalUser{

	@EJB
	private UserDAO userDao;
	
	@Override
	public boolean saveUser(User user) throws Exception {
		
		if(userWithEmailExists(user.getEmail())) {
//			throw new Exception("A user with that email + (" +user.getEmail() + ") already exists.");
		}
		
		
		return userDao.saveToDB(user);	
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}	
	
	@Override
	public User loginUser(String username, String password) throws Exception {
		User tryLoginUser = getUserByEmailAndPassword(username,password);	

		if(tryLoginUser==null) {
			throw new Exception("Not correct password for that user (or user do not exists)");			
		}

		if(tryLoginUser.getApprovedState()!=ApprovedState.GRANTED) {
			throw new Exception("This user is not approved by admin, pls try again later.");
		}
			
		return tryLoginUser;
	}
	
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public boolean deleteUser(int id) {
		return userDao.deleteUser(id);
	}	

	public User getUserByEmailAndPassword(String email, String password) {
		return userDao.getUserByEmailAndPassword(email,password);

	}
	
	private boolean userWithEmailExists(String email) {
		User user = userDao.getUserByEmail(email);
		return user != null;
	}

}

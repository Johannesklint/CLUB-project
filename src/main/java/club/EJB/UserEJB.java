package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;

import club.DAO.User;
import club.DAO.UserDAO;
import club.EJB.interfaces.LocalUser;

@Stateless
public class UserEJB implements LocalUser{

	@EJB
	private UserDAO userDao;
	
	@Override
	public boolean saveUser(User user) {
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

		if(!tryLoginUser.getApproved()) {
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

}

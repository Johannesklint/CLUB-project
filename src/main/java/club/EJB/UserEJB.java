package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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

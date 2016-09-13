package club.EJB;

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
	public boolean updateUser(User user) {
		return userDao.updateDB(user);
		
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

}

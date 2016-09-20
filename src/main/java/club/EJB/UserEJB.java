package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import club.DAO.User;
import club.DAO.User.ApprovedState;
import club.DAO.UserDAO;
import club.EJB.interfaces.LocalUser;
import club.backingBeans.user.LoginUserBean;

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
	public void loginUser(String username, String password, LoginUserBean loginUserBean) throws Exception {
		User tryLoginUser = getUserByEmailAndPassword(username,password);	

		if(tryLoginUser==null) {
			throw new Exception("Not correct password for that user (or user do not exists)");			
		}

		if(tryLoginUser.getApprovedState()!=ApprovedState.GRANTED) {
			throw new Exception("This user is not approved by admin, pls try again later.");
		}
			
		loginUserBean.onLogin(tryLoginUser);		
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

	@Override
	public boolean hasUniqueEmail(User user) {
		// TODO: fix this
		return true;
	}

}

package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import club.DAO.User;
import club.DAO.User.ApprovedState;
import club.DAO.UserDAO;
import club.EJB.interfaces.LocalUser;
import club.backingBeans.user.LoginUserBean;
import club.exceptions.LoginException;
import club.exceptions.ValidateException;
import club.password.CouldNotEncryptPasswordException;
import club.password.HMAC;
import club.password.PasswordHandler;

@Stateless
public class UserEJB implements LocalUser{

	@EJB
	private UserDAO userDao;
	
	@Override
	public User create(User user) {
		return userDao.saveToDB(user);	
	}

	@Override
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}	
	
	@Override
	public void loginUser(String username, String password, LoginHandlerable loginHandlerable) throws LoginException{
		User tryLoginUser = getUserByEmail(username);	

		if(tryLoginUser==null) {
			throw new LoginException("Not correct password for that user (or user do not exists)");			
		}
		
		if(  !PasswordHandler.isPasswordMatch(password, HMAC.buildfromString(tryLoginUser.getHashedPasswordSaltpair()) )) {
			throw new LoginException("Not correct password for that user (or user do not exists)");							
		}

		if(tryLoginUser.getApprovedState()!=ApprovedState.GRANTED) {
			throw new LoginException("This user is not approved by admin, pls try again later.");
		}
			
		loginHandlerable.onLogin(tryLoginUser);		
	}
	
	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public boolean deleteUser(int id) {
		return userDao.deleteUser(id);
	}	

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public void validateRegisterUser(User user) throws ValidateException {

		if(!hasUniqueEmail(user)) {
			throw new ValidateException("A user with that email (" +user.getEmail() + ") already exists.");
		}		
	}

	@Override
	public boolean hasUniqueEmail(User user) {
		
		String compareEmail = user.getEmail();
		for(User compareUser : userDao.getUsersByEmail(compareEmail)) {
			if(compareUser!=user) return false;
		}
		return true;
	}

	@Override
	public User update(User user) {
		return userDao.saveToDB(user);	
	}

}

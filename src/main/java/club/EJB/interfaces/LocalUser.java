package club.EJB.interfaces;

import javax.ejb.Local;

import club.DAO.user.User;
import club.EJB.LoginHandlerable;
import club.exceptions.LoginException;
import club.exceptions.ValidateException;

@Local
public interface LocalUser extends LocalGenericCrud<User> {
	
	User getUserByEmail(String email);
	User getUserByCpcid(String cpcid);
	void loginUser(String username, String password, LoginHandlerable loginHandlerable) throws LoginException;
	boolean hasUniqueEmail(User user);
	void validateRegisterUser(User user) throws ValidateException;
}

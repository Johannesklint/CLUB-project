package club.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import club.DAO.user.User;
import club.EJB.LoginHandlerable;
import club.exceptions.LoginException;
import club.exceptions.ValidateException;

@Local
public interface LocalUser extends LocalGenericCrud<User> {
	
	User getUserByEmail(String email);
	List<User> getAllValidUser();
	User getUserByCpcid(String cpcid);
	void loginUser(String username, String password, LoginHandlerable loginHandlerable) throws LoginException;
	boolean hasUniqueEmail(User user);
	void validateRegisterUser(User user) throws ValidateException;
	String getCalculatedAndValidCpcid(String firstName, String lastName);

}

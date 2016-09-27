package club.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;
import club.DAO.User;
import club.backingBeans.user.LoginUserBean;
import club.exceptions.LoginException;
import club.exceptions.ValidateException;

@Local
public interface LocalUser {
	User create(User user);
	User update(User user);
	User getUserById(int id);
	List<User> getAll();
	boolean deleteUser(int id);
	User getUserByEmailAndPassword(String email, String password);
	void loginUser(String username, String password, LoginUserBean loginUserBean) throws LoginException;
	boolean hasUniqueEmail(User user);
	void validateRegisterUser(User user) throws ValidateException;
}

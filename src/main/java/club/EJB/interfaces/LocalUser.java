package club.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;
import club.DAO.User;
import club.backingBeans.user.LoginUserBean;
import exceptions.FormException;

@Local
public interface LocalUser {
	boolean saveUser(User user);
	User getUserById(int id);
	List<User> getAll();
	boolean deleteUser(int id);
	User getUserByEmailAndPassword(String email, String password);
	void loginUser(String username, String password, LoginUserBean loginUserBean) throws Exception;
	boolean hasUniqueEmail(User user);
	void validateRegisterUser(User user) throws FormException;
}

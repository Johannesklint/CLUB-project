package club.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;
import club.DAO.User;

@Local
public interface LocalUser {
	boolean saveUser(User user);
	List<User> getAll();
	User getUserById(int selectedUserId);
	User getUserByEmailAndPassword(String email, String password);
}

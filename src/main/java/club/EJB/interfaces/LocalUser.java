package club.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;
import club.DAO.User;

@Local
public interface LocalUser {
	boolean saveUser(User user);
	boolean updateUser(User user);
	User getUserById(int id);
	List<User> getAll();
	User deleteUser(int id);
}

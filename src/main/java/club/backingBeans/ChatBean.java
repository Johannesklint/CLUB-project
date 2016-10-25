package club.backingBeans;

import javax.ejb.EJB;
import javax.inject.Named;

import club.DAO.user.User;
import club.EJB.interfaces.LocalUser;

@Named(value="chatBean")
public class ChatBean {

	@EJB
	private LocalUser userEJB;	

	public User getChatWith() {
		return userEJB.getById(1);
	}
	
}

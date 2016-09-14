package club.DAO;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import club.EJB.interfaces.LocalUser;


@Named("userManagement")
@RequestScoped
public class AdminUserManagementBean {
	
	private static final int INVALID_SELECTED_USER_ID = -1;
	@EJB
	private LocalUser userEJB;
	
	private int selectedUserId = INVALID_SELECTED_USER_ID;
	

	public String approveSelectedUser() {
		if(selectedUserId == INVALID_SELECTED_USER_ID) return "admin-user-management";
		
		User userToApprove = userEJB.getUserById(selectedUserId);
		
		if(userToApprove == null) return "admin-user-management";
		
		userToApprove.setApproved(true);
		userEJB.saveUser(userToApprove);
		return "admin-user-management";
	}
	
	public List<User> getAllUsers() {
		List<User> users = userEJB.getAll();
		return users;
	}
	
	public List<User> getAllUnapproved() {
		return getAllUsersMatching(user -> !user.getApproved());
	}
	
	public List<User> getAllApproved() {
		return getAllUsersMatching(user -> user.getApproved());
	}
	
	
	
	private List<User> getAllUsersMatching(Predicate<User> predicate) {
		List<User> users = userEJB.getAll();
		return users.stream()
				.filter(predicate)
				.collect(Collectors.toList());
	}
	
	public int getSelectedUserId() {
		return selectedUserId;
	}
	
	public void setSelectedUserId(int selectedUserId) {
		this.selectedUserId = selectedUserId;
	}
}

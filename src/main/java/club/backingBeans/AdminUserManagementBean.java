package club.backingBeans;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import club.DAO.User;
import club.EJB.interfaces.LocalUser;


@Named("userManagement")
@RequestScoped
public class AdminUserManagementBean {
	
	@EJB
	private LocalUser userEJB;	
	// Workaround for not being able to send User as a parameter to changeApprovedStatus
	// see more at: admin-user-management-table.xhtml (<f:setPropertyActionListener ....)
	private User selectedUser; // used to know which user needs to change status etc.
	
	public String changeApprovedStatus() {
		
		System.out.println("changing approved status for user " + selectedUser.getFirstName());
		// switch the boolean value
		boolean previousValue = selectedUser.getApproved();
		selectedUser.setApproved(!previousValue);
		try {
			userEJB.saveUser(selectedUser);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The passwords do not match."));
		}
		
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
	
	public User getSelectedUser() {
		return selectedUser;
	}
	
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
}

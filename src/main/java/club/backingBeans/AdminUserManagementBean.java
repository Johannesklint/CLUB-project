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
import club.DAO.User.ApprovedState;
import club.EJB.interfaces.LocalUser;


@Named("userManagement")
@RequestScoped
public class AdminUserManagementBean {
	
	@EJB
	private LocalUser userEJB;	
	// Workaround for not being able to send User as a parameter to changeApprovedStatus
	// see more at: admin-user-management-table.xhtml (<f:setPropertyActionListener ....)
	private User selectedUser; // used to know which user needs to change status etc.
	

	public String denyApprovement() {
		selectedUser.setApprovedState(ApprovedState.DENIED);
		try {
			userEJB.saveUser(selectedUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin-user-management";
	}
	
	public String grantApprovement() {
		selectedUser.setApprovedState(ApprovedState.GRANTED);
		try {
			userEJB.saveUser(selectedUser);
		} catch (Exception e) {
			// TODO: have a save approved state method. then we do not have to validate email-conflict
			e.printStackTrace();
		}
		return "admin-user-management";
	}
	
	public List<User> getAllUsers() {
		List<User> users = userEJB.getAll();
		return users;
	}
	
	public List<User> getAllApprovedGranted() {
		return getAllUsersMatching(user -> user.getApprovedState()==ApprovedState.GRANTED);
	}

	public List<User> getAllApprovedDenied() {
		return getAllUsersMatching(user -> user.getApprovedState()==ApprovedState.DENIED);
	}

	public List<User> getAllApprovedPending() {
		return getAllUsersMatching(user -> user.getApprovedState()==ApprovedState.PENDING);
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

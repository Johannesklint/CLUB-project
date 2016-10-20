package club.backingBeans;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.user.User;
import club.DAO.user.User.ApprovedState;
import club.EJB.interfaces.LocalUser;
import club.backingBeans.user.LoginUserBean;


@Named("userManagementBean")
@RequestScoped
public class UserManagementBean extends BasicFrontendBean{
	
	@EJB
	private LocalUser userEJB;	

	@Inject @Named(value="loginUserBean")
	private LoginUserBean loginUserBean;
	
	// Workaround for not being able to send User as a parameter to changeApprovedStatus
	// see more at: admin-user-management-table.xhtml (<f:setPropertyActionListener ....)
	private User selectedUser; // used to know which user needs to change status etc.

	@PostConstruct
	public void init() {
	}

	public String switchAdminState() {
		
		if(selectedUser.getAdmin()==false) {
			selectedUser.setAdmin(true);
		} else {
			selectedUser.setAdmin(false);			
		}

		if(userEJB.update(selectedUser)!=null) {
			
			return "";			
		}
		return ""; //TODO: handle erroe
	}

	public String denyApprovement() {
		selectedUser.setApprovedState(ApprovedState.DENIED);
		userEJB.update(selectedUser);
		return "";
	}
	
	public String grantApprovement() {
		selectedUser.setApprovedState(ApprovedState.GRANTED);
		userEJB.update(selectedUser);
		return "";
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
	
	public User getSelectedUser() {
		return selectedUser;
	}	
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	
	private List<User> getAllUsersMatching(Predicate<User> predicate) {
		List<User> users = userEJB.getAll();
		return users.stream()
				.filter(predicate)
				.collect(Collectors.toList());
	}

}

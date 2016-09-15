package club.backingBeans.user;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.User;
import club.EJB.interfaces.LocalUser;

@Named(value="user")
@RequestScoped
@Startup
public class UserBean {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String repeatPassword;
	private boolean admin;
	private boolean approved;
	
	@Named(value="loginUser")
	@Inject
	private LoginUserBean loginUser;
	
	@EJB
	private LocalUser userEJB;
	
	public UserBean() {
		
	}
	
	@PostConstruct
	public void Init(){
		this.email = loginUser.getUser().getEmail();
		this.password = loginUser.getUser().getPassword();
	
	}
	
	
	public String saveUser() {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setAdmin(admin);
		user.setApproved(approved);
		
		if (userEJB.saveUser(user)) {
			this.firstName = null;
			this.lastName = null;
			this.email = null;
			this.password = null;
			return "wait-for-approve-index";		

		}
		return ""; //TODO: make sure this is the right way to 'redirect' to same page		
	}
	
	public String updateUser() {
		User loggedInUser = loginUser.getUser();		
		
		if(password.equals(repeatPassword)){
			loggedInUser.setEmail(email);	
			loggedInUser.setPassword(password);
			loggedInUser.setAdmin(true);
			loggedInUser.setApproved(false);
			
			if (userEJB.saveUser(loggedInUser)) {									
				return "update-user-index";		
			}
			
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The passwords do not match."));
		}
		
		return "update-user-index";
	}
	
	
	public UserBean getUserById(){
		User user = userEJB.getUserById(1);
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setEmail(user.getEmail());
		setPassword(user.getPassword());
		setAdmin(user.getAdmin());
		setApproved(user.getApproved());
		
		return this;
	}
	
	public String deleteUser(){
		if(userEJB.deleteUser(loginUser.getUser().getId())){
			loginUser.doLogin();
			return "update-user-index";
		}
			
		return "update-user-index";
	}
	
	
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
}

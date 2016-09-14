package club.domain;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import club.DAO.User;
import club.EJB.interfaces.LocalUser;

@Named(value="user")
@RequestScoped
public class UserBean {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	private boolean admin;
	private boolean approved;
	
	@EJB
	private LocalUser userEJB;
	
	public UserBean() {
		
	}
	

	public UserBean(String firstName, String lastName, String email, String password, boolean admin, boolean approved) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.admin = admin;
		this.approved = approved;
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
			return "index";		

		}
		return "index";		
		
	}
	
	public String updateUser() {		
		User userUpd = userEJB.getUserById(1);
		
		userUpd.setFirstName(firstName);
		userUpd.setLastName(lastName);
		userUpd.setEmail(email);	
		userUpd.setPassword(password);
		userUpd.setAdmin(true);
		userUpd.setApproved(false);
		
		if (userEJB.saveUser(userUpd)) {									
				return "update-user-index";		

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
	
	public UserBean deleteUser(){
		
		return this;
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

	

}

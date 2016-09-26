package club.backingBeans.user;

import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import club.DAO.User;
import club.DAO.User.ApprovedState;
import club.EJB.interfaces.LocalUser;
import exceptions.FormException;

@Named(value="registerUser")
@RequestScoped
@Startup
public class RegisterUserBean {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean admin;
	private boolean approved;
	private Boolean termsAndConditions;

	
	@EJB
	private LocalUser userEJB;
	
	private void validateRegisterUser(User user) throws FormException {

		if(!userEJB.hasUniqueEmail(user)) {
			throw new FormException("A user with that email (" +user.getEmail() + ") already exists.");
		}		
	}
	
	
	
	public String saveUser() {
		System.out.println("SAVEUSER");
		if(termsAndConditions == true){
			System.out.println("inne i true");
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);
			user.setAdmin(admin);
			user.setApprovedState(ApprovedState.PENDING);
			
			try {
				validateRegisterUser(user);
			} catch (FormException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
				return "register-user-index";
			}
	
			boolean isSaved = false;
			isSaved = userEJB.saveUser(user);
	
			
			if (isSaved) {
				this.firstName = null;
				this.lastName = null;
				this.email = null;
				this.password = null;
				return "wait-for-approve-index";		
	
			}
		}else{
			System.out.println("FAN" + termsAndConditions);
		}
		return "index"; //TODO: make sure this is the right way to 'redirect' to same page		
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
	public Boolean getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(Boolean termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}	

}

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
import club.backingBeans.BasicFrontendBean;
import club.exceptions.ValidateException;

@Named(value="registerUserBean")
@RequestScoped
//@Startup
public class RegisterUserBean extends BasicFrontendBean{
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean admin;
	private boolean approved;
	private Boolean termsAndConditions;
	
	@EJB
	private LocalUser userEJB;		
	
	public String create() {
			
		User user = buildFromFields();
		
		try {
			userEJB.validateRegisterUser(user);

			if (userEJB.create(user) != null) {
				return "wait-for-approve";			
			}else {
				super.addFacesMessage("User could not be saved");
				return "";
			}
			
		} catch (ValidateException e) {
			super.addFacesMessage(e.getMessage());
			return "";
		}		

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
	
	private User buildFromFields() {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.generateHMACpassword(password);
		user.setAdmin(admin);
		user.setApprovedState(ApprovedState.PENDING);
		return user;
	}
}

package club.backingBeans.user;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.user.User;
import club.EJB.interfaces.LocalUser;
import club.backingBeans.BasicFrontendBean;

@Named(value="userProfileBean")
@RequestScoped
@Startup
public class UserProfileBean extends BasicFrontendBean{

	private String email;
	private String cpcid;
	private String password;
	private String repeatPassword;
	
	@Named(value="loginUserBean")
	@Inject
	private LoginUserBean loginUser;
	
	private User user;
	
	@EJB
	private LocalUser userEJB;
	
	public UserProfileBean() {
		
	}
	
	@PostConstruct	
	public void Init(){
		fetchUserFromLogin();
		this.email = user.getEmail();		
		this.cpcid = user.getCpcid();		
	}
		
	public String update() {	
		
		if(password.equals(repeatPassword)){
			user.setEmail(email);	
			user.setCpcid(cpcid);	
			user.generateNewHMACpassword(password);
			
			if (userEJB.update(user) != null) {  									
				return "home";		
			}else {
				super.addFacesMessage("Could not update");
				return "";
			}

		}else{
			super.addFacesMessage("Passwords do not match");
			return "";

		}
		
	}
	
	public String delete(){
		
		if(userEJB.delete(user.getId())){
			loginUser.doLogout();			
			return "index";	
		}else {
			super.addFacesMessage("Could not delete user");
			return "";
		}
			
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpcid() {
		return cpcid;
	}
	public void setCpcid(String cpcid) {
		this.cpcid = cpcid;
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
	private void fetchUserFromLogin(){
		this.user = loginUser.getUser();
	}

}

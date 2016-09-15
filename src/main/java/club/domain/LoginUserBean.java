package club.domain;

import javax.inject.Named;
import javax.validation.constraints.Size;

import club.DAO.User;
import club.EJB.interfaces.LocalUser;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value="loginUser")
@SessionScoped
public class LoginUserBean implements Serializable {

	// TODO: tror inte vi kan blanda sessionscope och requestscope.
	// därför måste vi sära på UserLogin och vanliga User. Kanske ska ha en frontend bean för varje action istället för entitet?
	
	private static final long serialVersionUID = -6514760360423746740L;

	private String username;
    private String password;
    private User tryLoginUser;
    
	@EJB
	private LocalUser userEJB;

    public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public User getUser() {
		return tryLoginUser;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidLogin() {
		return tryLoginUser != null;
	}

	public String doLogout() {
		tryLoginUser = null;
		return "login-index";
	}
	
	public String doLogin() {
		
		tryLoginUser = userEJB.getUserByEmailAndPassword(username,password);	
		
		if(tryLoginUser==null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Not correct password for that user (or user do not exists)"));;//.addMessage(null, new FacesMessage(message));
			
			return "login-index";
		}
		else if(tryLoginUser.getApproved()) {
			//clear fields when login success
			password = null;
			username = null;			

		}else{
			tryLoginUser = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("This user is not approved by admin, pls try again later."));

			return "login-index";

		}
		return "home-index";
	}
	

}

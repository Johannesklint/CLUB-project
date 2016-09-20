package club.backingBeans.user;

import javax.inject.Named;
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
    private User loggedInUser;
    
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
		return loggedInUser;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidLogin() {
		return loggedInUser != null;
	}

	public String doLogout() {
		loggedInUser = null;
		return "login-index";
	}
	
	public String doLogin() {
		
		try {
			userEJB.loginUser(username,password,this);
			return "home-index";				
		}
		catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));//.addMessage(null, new FacesMessage(message));
			return "login-index";
		}

	}

	public void onLogin(User user) {
		this.loggedInUser = user;
	}
	

}

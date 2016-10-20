package club.backingBeans.user;

import javax.inject.Named;

import club.DAO.user.User;
import club.EJB.LoginHandlerable;
import club.EJB.interfaces.LocalUser;
import club.backingBeans.BasicFrontendBean;
import club.exceptions.LoginException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;


@Named(value="loginUserBean")
@SessionScoped
public class LoginUserBean extends BasicFrontendBean implements Serializable, LoginHandlerable {
	
	private static final long serialVersionUID = -6514760360423746740L;

	private String username;
    private String password;
    private User loggedInUser;
    
	@EJB
	private LocalUser userEJB;

	public String doLogin() {
		
		try {
			userEJB.loginUser(username,password,this);
			return "home.xhtml?faces-redirect=true";				
		}
		catch(LoginException e) {
			super.addFacesMessage(e.getMessage());
			return "login";
		}
		
	}
	
	public String doLogout() {
		loggedInUser = null;
		return "index.xhtml?faces-redirect=true";
	}
	
	public void onLogin(User user) {
		this.loggedInUser = user;
	}
	
	
    public boolean isLoggedIn() {
    	return this.loggedInUser != null;
    }

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

}

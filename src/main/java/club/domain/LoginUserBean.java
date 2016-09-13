package club.domain;

import javax.inject.Named;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

@Named(value="loginUser")
@SessionScoped
public class LoginUserBean implements Serializable {

	private static final long serialVersionUID = -6514760360423746740L;

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String doLogin() {
		
		return "index";
	}
}

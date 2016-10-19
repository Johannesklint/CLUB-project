package club.backingBeans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

import club.backingBeans.user.LoginUserBean;

public abstract class BasicFrontendBean {
	
	public void addFacesMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}
	
	/**
	 * Like {@link #redirectIfNotLoggedIn(LoginUserBean)} but with admin check too
	 * @param loginUserBean
	 */
	protected void redirectIfNotAdmin(LoginUserBean loginUserBean) {
		redirectIfNotLoggedIn(loginUserBean);
		if(loginUserBean.getUser().getAdmin() == true) return;
	}
	
	protected void redirectIfNotLoggedIn(LoginUserBean loginUserBean) {
		if(loginUserBean.isLoggedIn()) return;
	}
	

}

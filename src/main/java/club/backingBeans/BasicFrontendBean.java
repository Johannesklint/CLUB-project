package club.backingBeans;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

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
		redirectToRoot();
	}
	
	protected void redirectIfNotLoggedIn(LoginUserBean loginUserBean) {
		if(loginUserBean.isLoggedIn()) return;
		redirectToRoot();
	}
	
	private void redirectToRoot() {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/");
		} catch (IOException e) {
			//TODO: this catch will make the none-loggedin user able to use the bean that would, otherwise, redirect if not logged in. This is bad!
			e.printStackTrace();
		}
	}

}

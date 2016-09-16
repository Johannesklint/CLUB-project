package club.backingBeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class BasicFrontendBean {
	public void addFacesMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
	}
}

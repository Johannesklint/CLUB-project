package club.backingBeans.event;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.event.Event;
import club.DAO.user.User;
import club.backingBeans.post.PostGetterBean;
import club.backingBeans.user.LoginUserBean;

@Named(value="addAttendantOnEventBean")
@RequestScoped
public class AddAttendantOnEventBean extends EventBean {

	
	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;

	@Inject @Named(value="postGetterBean")
	private PostGetterBean postGetterBean;

	public String submit() {
		super.setId(postGetterBean.getAsEvent().getId());
		return updateAndGetRedirect();		
	}
	
	
	@Override
	public Event getFromFields() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Event updateFromFields() {
		
		User userToAttend = loginUserBean.getUser();
		if(userToAttend==null) throw new RuntimeException("Not logged in when try attend");
		
		Event eventToUpdate = (Event)super.getById(getId());
			eventToUpdate.getAttendees().add(userToAttend);

			//throw new RuntimeException("Not logged in when try attend");
		
		
		return eventToUpdate;
	}

}

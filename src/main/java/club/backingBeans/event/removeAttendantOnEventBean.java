package club.backingBeans.event;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.RuntimeErrorException;

import club.DAO.Event;
import club.DAO.User;
import club.backingBeans.EventBean;
import club.backingBeans.PostGetterBean;
import club.backingBeans.user.LoginUserBean;

@Named(value="removeAttendantOnEventBean")
@RequestScoped
public class removeAttendantOnEventBean extends EventBean{
	
	
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
		
		User userToNotAttend = loginUserBean.getUser();
		if(userToNotAttend == null) throw new RuntimeException("Not logged in when try unattend");
		
		Event eventToUpdate = (Event)super.getById(getId());
		
		eventToUpdate.getAttendees().remove(userToNotAttend);
		
		System.out.println(eventToUpdate.getAttendees().size());
		return eventToUpdate;
				
	}
}

package club.backingBeans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.RuntimeErrorException;

import club.DAO.Event;
import club.DAO.News;

@Named(value="eventUpdateBean")
@RequestScoped
public class EventUpdateBean extends EventBean {
	
	@Inject @Named(value="postGetterBean")
	private PostGetterBean postGetterBean;
		
	@PostConstruct
	public void init() {
		System.out.println("update bean INIT");
		Event event = postGetterBean.getAsEvent();
		setBeanFieldsFromEntity(event);
	}
	
	public String submit() {
		System.out.println("submitting update");
		return updateAndGetRedirect();
	}
		
	private void setBeanFieldsFromEntity(Event event) {
		System.out.println("setting fields from " + event);
		
		super.setId(event.getId());
		super.setTitle(event.getTitle());
		super.setText(event.getText());
		super.setStartTime(event.getStartTime());
		super.setDurationInMinutes(event.getDurationInMinutes());
	}
	
	@Override
	public Event updateFromFields() {
		
		Event eventToUpdate = (Event)super.getById(getId());
		eventToUpdate.setTitle(super.getTitle());
		eventToUpdate.setText(super.getText());
		eventToUpdate.setDurationInMinutes(super.getDurationInMinutes());
		return eventToUpdate;
	}

	@Override
	public Event getFromFields() {
		// TODO Auto-generated method stub
		return null;
	}	
}

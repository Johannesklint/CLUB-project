package club.backingBeans.event;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.event.Event;
import club.backingBeans.post.PostGetterBean;

@Named(value="eventUpdateBean")
@RequestScoped
public class EventUpdateBean extends EventBean {
	
	@Inject @Named(value="postGetterBean")
	private PostGetterBean postGetterBean;
		
	@PostConstruct
	public void init() {
		Event event = postGetterBean.getAsEvent();
		setBeanFieldsFromEntity(event);
	}
	
	public String submit() {
		return updateAndGetRedirect();
	}
		
	private void setBeanFieldsFromEntity(Event event) {
		super.setId(event.getId());
		super.setTitle(event.getTitle());
		super.setText(event.getText());
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
		throw new RuntimeException("This should be never been called since this is a update-based bean");
	}		
}

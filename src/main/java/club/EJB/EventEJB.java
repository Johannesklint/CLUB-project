package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.event.Event;
import club.DAO.event.EventDAO;
import club.EJB.interfaces.LocalEvent;

@Stateless
public class EventEJB implements LocalEvent {
	
	@EJB
	private EventDAO eventDAO;

	@Override
	public Event save(Event entity) {
		return eventDAO.save(entity);
	}

	@Override
	public boolean delete(int id) {
		return eventDAO.delete(id); 
	}

	@Override
	public List<Event> getAll() {
		return eventDAO.getAll(false);
	}

	@Override
	public Event getById(int id) {
		return eventDAO.getById(id);		
	}

}

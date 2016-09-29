package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.Event;
import club.DAO.EventDAO;
import club.DAO.News;
import club.EJB.interfaces.LocalEvent;
import club.EJB.interfaces.LocalNews;


@Stateless
public class NewsEJB implements LocalEvent{
	
	@EJB
	private EventDAO eventDAO;

	@Override
	public Event save(Event event) {
		return eventDAO.save(event);
	}

	@Override
	public List<Event> getAll() {
	
		return eventDAO.getAll();
	}

	@Override
	public Event getById(int id) {
		return eventDAO.getById(id);
	}

	@Override
	public boolean delete(int id) {
		return eventDAO.delete(id);
	}
	
}

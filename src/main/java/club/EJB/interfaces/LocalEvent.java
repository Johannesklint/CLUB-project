package club.EJB.interfaces;

import java.util.List;

import club.DAO.Event;

public interface LocalEvent {

	Event save(Event event);

	List<Event> getAll();

	Event getById(int id);

	boolean delete(int id);

}
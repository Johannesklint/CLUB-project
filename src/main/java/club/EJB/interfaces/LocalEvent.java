package club.EJB.interfaces;

import club.DAO.event.Event;

//NOTE: does not need to have @Local, since LocalGenericCrud already has that annotation
public interface LocalEvent extends LocalGenericCrud<Event> {
	// Nothing here, unless we want something more fancy than
	// the basic CRUD that LocalGenericCrud<Event> provides
}
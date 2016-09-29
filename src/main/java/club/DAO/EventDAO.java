package club.DAO;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class EventDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public Event save(Event event) {
		return entityManager.merge(event);
	}

	@SuppressWarnings("unchecked")
	public List<Event> getAll() {
		Query query = entityManager.createNamedQuery("Event.findAll");
		List<Event> event = (List<Event>)query.getResultList();
		
		//TODO: since both Events and News have same 'do not show if' statement, this statement should be moved to Post somehow
		return event.stream()
				.filter( e -> !e.getHidden())
				.collect(Collectors.toList());
	}

	public Event getById(int id) {
		return entityManager.find(Event.class, id);
	}

	public boolean delete(int id) {
		Event event = entityManager.find(Event.class, id);
		if(event != null){
			entityManager.remove(event);
			return true;
		}
		return false;
	}
	
	

}

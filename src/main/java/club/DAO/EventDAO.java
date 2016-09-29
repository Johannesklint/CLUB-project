package club.DAO;

import java.util.List;
import java.util.stream.Collector;
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

	public News getNewsById(int selectedNewsId) {
		return entityManager.find(News.class, selectedNewsId);
	}

	public boolean deleteNews(int id) {
		News news = entityManager.find(News.class, id);
		if(news != null){
			entityManager.remove(news);
			return true;
		}
		return false;
	}
	
	

}

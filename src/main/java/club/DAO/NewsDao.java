package club.DAO;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful
public class NewsDao {
	
	@PersistenceContext
	private EntityManager manager;

	public boolean save(News news) {
		return manager.merge(news) != null;
	}

	@SuppressWarnings("unchecked")
	public List<News> getAll() {
		Query query = manager.createNamedQuery("News.findAll");
		List<News> news = (List<News>)query.getResultList();
		
		return news;
	}
	
	

}

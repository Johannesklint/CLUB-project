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
	private EntityManager entityManager;

	public boolean save(News news) {
		return entityManager.merge(news) != null;
	}

	@SuppressWarnings("unchecked")
	public List<News> getAll() {
		Query query = entityManager.createNamedQuery("News.findAll");
		List<News> news = (List<News>)query.getResultList();
		
		return news;
	}

	public News getNewsById(int selectedNewsId) {
		return entityManager.find(News.class, selectedNewsId);
	}
	
	

}

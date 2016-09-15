package club.DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class NewsDao {
	
	@PersistenceContext
	private EntityManager manager;

	public boolean save(News news) {
		return manager.merge(news) != null;
	}
	
	

}

package club.EJB.interfaces;

import java.util.List;
import javax.ejb.Local;

import club.DAO.News;

@Local
public interface LocalNews {

	News saveNews(News news);

	boolean deleteNews(int id);
	
	List<News> getAll();

	News getNewsById(int selectedNewsId);

	
}

package club.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import club.DAO.News;

@Local
public interface LocalNews {

	boolean saveNews(News news);

	List<News> getAll();

	News getNewsById(int selectedNewsId);
		
}

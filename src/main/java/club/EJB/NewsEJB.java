package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.News;
import club.DAO.NewsDao;
import club.EJB.interfaces.LocalNews;


@Stateless
public class NewsEJB implements LocalNews{
	
	@EJB
	private NewsDao newsDao;

	@Override
	public boolean saveNews(News news) {
		return newsDao.save(news);
	}

	@Override
	public List<News> getAll() {
	
		return newsDao.getAll();
	}
	
}

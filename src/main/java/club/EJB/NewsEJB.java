package club.EJB;

import javax.ejb.Stateless;

import club.DAO.News;
import club.DAO.NewsDao;
import club.EJB.interfaces.LocalNews;


@Stateless
public class NewsEJB implements LocalNews{
	
	private NewsDao newsDao;

	@Override
	public boolean saveNews(News news) {
		return newsDao.save(news);
	}
	
}

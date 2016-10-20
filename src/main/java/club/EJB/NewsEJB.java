package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.news.News;
import club.DAO.news.NewsDAO;
import club.EJB.interfaces.LocalNews;


@Stateless
public class NewsEJB implements LocalNews{
	
	@EJB
	private NewsDAO newsDao;

	@Override
	public News save(News entity) {
		return newsDao.save(entity);
	}

	@Override
	public boolean delete(int id) {
		return newsDao.delete(id);
	}

	@Override
	public List<News> getAll() {
		return newsDao.getAll(false);
	}

	@Override
	public News getById(int id) {
		return newsDao.getById(id);
	}

	@Override
	public News update(News entity) {
		// TODO: use this in update
		return null;
	}


	
}

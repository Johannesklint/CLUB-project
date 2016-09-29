package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.News;
import club.DAO.NewsDao;
import club.EJB.interfaces.LocalNews;

//
//@Stateless
//public class EventEJB implements LocalNews{
//	
//	@EJB
//	private NewsDao newsDao;
//
//	@Override
//	public News saveNews(News news) {
//		return newsDao.save(news);
//	}
//
//	@Override
//	public List<News> getAll() {
//	
//		return newsDao.getAll();
//	}
//
//	@Override
//	public Event getNewsById(int selectedNewsId) {
//		return newsDao.getNewsById(selectedNewsId);
//	}
//
//	@Override
//	public boolean deleteNews(int id) {
//		return newsDao.deleteNews(id);
//	}
//	
//}

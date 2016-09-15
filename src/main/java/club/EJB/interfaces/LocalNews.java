package club.EJB.interfaces;

import javax.ejb.Local;

import club.DAO.News;

@Local
public interface LocalNews {

	boolean saveNews(News news);

}

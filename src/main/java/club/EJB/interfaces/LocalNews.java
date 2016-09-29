package club.EJB.interfaces;

import club.DAO.News;

//NOTE: does not need to have @Local, since LocalGenericCrud already has that annotation
public interface LocalNews extends LocalGenericCrud<News> {
	// Nothing here, unless we want something more fancy than
	// the basic CRUD that LocalGenericCrud<News> provides
}

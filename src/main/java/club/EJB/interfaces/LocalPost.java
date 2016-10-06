package club.EJB.interfaces;

import club.DAO.Post;

//NOTE: does not need to have @Local, since LocalGenericCrud already has that annotation
public interface LocalPost extends LocalGenericCrud<Post> {
	// Nothing here, unless we want something more fancy than
	// the basic CRUD that LocalGenericCrud<News> provides
}

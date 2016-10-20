package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.news.News;
import club.DAO.news.NewsDAO;
import club.DAO.post.Post;
import club.DAO.post.PostDAO;
import club.EJB.interfaces.LocalNews;
import club.EJB.interfaces.LocalPlatform;
import club.EJB.interfaces.LocalPost;


@Stateless
public class PostEJB implements LocalPost {
	
	@EJB
	PostDAO postDao;
	
	@Override
	public Post save(Post entity) {
		return postDao.save(entity);
	}

	@Override
	public boolean delete(int id) {
		
		Post post = postDao.getById(id);
		post.setHidden(true);
		return postDao.save(post) != null;
	}

	@Override
	public List<Post> getAll() {
		return postDao.getAll(false);
	}

	@Override
	public Post getById(int id) {
		return postDao.getById(id);
	}

	@Override
	public Post update(Post entity) {
		// TODO use this!!!
		return null;
	}

}

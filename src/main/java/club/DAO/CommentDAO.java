package club.DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import club.DAO.Comment;

@Stateful
public class CommentDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public boolean save(Comment comment) {
		return manager.merge(comment) != null;
	}

}

package club.DAO;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import club.DAO.Comment;
import club.exceptions.MergeNullException;

@Stateful
public class CommentDAO {
	
	@PersistenceContext
	private EntityManager manager;

	public boolean save(Comment comment) {
		return manager.merge(comment) != null;
	}

	public Comment getById(int id) {
		return manager.find(Comment.class, id);
	}

	public Comment update(Comment comment) {
		Comment savedComment = manager.merge(comment);
		if(savedComment==null) throw new MergeNullException();
		return savedComment;
	}

}

package club.DAO.comment;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import club.DAO.GenericCrudDao;

@Stateful
public class CommentDAO extends GenericCrudDao<Comment> {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Comment update(Comment comment) {
		Comment savedComment = super.update(comment);
		return savedComment;
	}

	@SuppressWarnings("unchecked")
	public List<Comment> getAllByPostId(int postId) {
		Query query = manager.createNamedQuery("Comment.findAllByPostId");
		query.setParameter("postId", postId);
		List<Comment> comments = (List<Comment>)query.getResultList();
		return comments;
	}

}

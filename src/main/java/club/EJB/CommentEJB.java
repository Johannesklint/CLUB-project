package club.EJB;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.comment.Comment;
import club.DAO.comment.CommentDAO;
import club.EJB.interfaces.LocalComment;
import club.exceptions.ValidateException;

@Stateless
public class CommentEJB implements LocalComment {

	@EJB
	CommentDAO commentDao;
	
	@Override
	public Comment save(Comment comment) {
		return commentDao.save(comment);
	}
	
	@Override
	public void validateComment(Comment comment) throws ValidateException {
		// Since the input text validation (required and less and equal than 140) is in JSF-component.
		// and relation to author and post can not be set in frontend (and validated in Entity)
		// there is no reason to have anything here. Keeps a method here for continullity
		return;
	}

	@Override
	public Comment getById(int id) {
		return commentDao.getById(id);
	}

	@Override
	public Comment update(Comment comment) {
		return commentDao.update(comment);
	}
	
	@Override
	public List<Comment> getAllByPostId(int postId) {
		return commentDao.getAllByPostId(postId);
	}

	@Override
	public boolean delete(int id) {
		// N/A	
		return false;
	}

	@Override
	public List<Comment> getAll() {
		// N/A
		return null;
	}

}

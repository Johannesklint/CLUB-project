package club.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.Comment;
import club.DAO.CommentDAO;
import club.EJB.interfaces.LocalComment;
import club.exceptions.ValidateException;

@Stateless
public class CommentEJB implements LocalComment {

	@EJB
	CommentDAO commentDao;
	
	@Override
	public boolean saveComment(Comment comment) {
		return commentDao.save(comment);
	}
	
	@Override
	public void validateComment(Comment comment) throws ValidateException {
		// Since the input text validation (required and less and equal than 140) is in JSF-component.
		// and relation to author and post can not be set in frontend (and validated in Entity)
		// there is no reason to have anything here. Keeps a method here for continullity
		return;
	}

}

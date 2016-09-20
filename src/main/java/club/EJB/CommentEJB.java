package club.EJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.Comment;
import club.DAO.CommentDAO;
import club.EJB.interfaces.LocalComment;

@Stateless
public class CommentEJB implements LocalComment {

	@EJB
	CommentDAO commentDao;
	
	@Override
	public boolean saveComment(Comment comment) {
		return commentDao.save(comment);
	}

}

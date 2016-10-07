package club.EJB.interfaces;

import javax.ejb.Local;

import club.DAO.Comment;
import club.exceptions.ValidateException;

@Local
public interface LocalComment {
	
	public boolean saveComment(Comment comment); // TODO: I think this should be deprecated
	public Comment updateComment(Comment comment);

	void validateComment(Comment comment) throws ValidateException;

	public Comment getById(int selectedCommentId);

}

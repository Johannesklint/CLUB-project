package club.EJB.interfaces;

import javax.ejb.Local;

import club.DAO.Comment;
import exceptions.FormException;

@Local
public interface LocalComment {
	
	public boolean saveComment(Comment comment);

	void validateComment(Comment comment) throws FormException;

}

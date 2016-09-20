package club.EJB.interfaces;

import javax.ejb.Local;

import club.DAO.Comment;

@Local
public interface LocalComment {
	
	public boolean saveComment(Comment comment);

}

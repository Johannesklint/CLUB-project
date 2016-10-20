package club.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

import club.DAO.comment.Comment;
import club.exceptions.ValidateException;

@Local
public interface LocalComment extends LocalGenericCrud<Comment> {
	
	void validateComment(Comment comment) throws ValidateException;
	public List<Comment> getAllByPostId(int postId);

}

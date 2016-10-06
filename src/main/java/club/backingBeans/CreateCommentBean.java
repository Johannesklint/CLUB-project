package club.backingBeans;

import java.sql.Timestamp;
import java.time.Instant;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import club.DAO.Comment;
import club.DAO.Post;
import club.EJB.interfaces.LocalComment;
import club.exceptions.ValidateException;

@Named(value="createCommentBean")
@RequestScoped
public class CreateCommentBean extends CommentBean{

	@EJB
	private LocalComment commentEJB;

	@Inject @Named(value="postGetterBean")
	private PostGetterBean postGetterBean;

	public String submit() {
		
		return createComment();
	}
	
	private String createComment() {
		
		Comment comment = new Comment();		
		comment.setCreated(Timestamp.from(Instant.now()));
		comment.setText(super.getText());
		comment.setUser(super.getAuthor());
		Post post = postGetterBean.getAsPost();
		comment.setPost(post);
		try {
			commentEJB.validateComment(comment);
			commentEJB.updateComment(comment);
		}
		catch(ValidateException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
			return "";
		}
		
		return "post-details.xhtml?faces-redirect=true&id=" + post.getId();
		
	}

}

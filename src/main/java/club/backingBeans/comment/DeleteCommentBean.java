package club.backingBeans.comment;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.comment.Comment;
import club.EJB.interfaces.LocalComment;
import club.backingBeans.post.PostGetterBean;

@Named(value="deleteCommentBean")
@RequestScoped
public class DeleteCommentBean extends CommentBean{

	@EJB
	private LocalComment commentEJB;
	
	@Inject
	private PostGetterBean postGetterBean;
	
	public String submit(int id){
		
		int postId = postGetterBean.getAsPost().getId();
		Comment commentToDelete = commentEJB.getById(id);
		commentToDelete.setHidden(true);
		commentEJB.save(commentToDelete);
		return "post-details.xhtml?faces-redirect=true&id=" + postId;
	}
}

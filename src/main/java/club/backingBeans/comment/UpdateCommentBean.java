package club.backingBeans.comment;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.comment.Comment;
import club.EJB.interfaces.LocalComment;
import club.backingBeans.user.LoginUserBean;

@Named(value="updateCommentBean")
@RequestScoped
public class UpdateCommentBean extends CommentBean{
	
	@Inject @Named(value="loginUserBean")
	LoginUserBean loginUserBean;

	@EJB
	private LocalComment commentEJB;
	
	@PostConstruct
	public void init() {
	}
	
	public String submit(){
		return update();
	}
	
	public String update(){
		// TODO: remove whole class?!?!?
		return "";
//		Comment commentToUpdate = commentEJB.getById(super.getSelectedCommentId());
//		commentToUpdate.setText(super.getText());
//		commentEJB.update(commentToUpdate);
//		return "post-details.xhtml?faces-redirect=true&id=" + commentToUpdate.getPost().getId();
	}

}

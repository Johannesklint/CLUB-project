package club.backingBeans.comment;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import club.DAO.Comment;
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
		super.redirectIfNotLoggedIn(loginUserBean);
	}
	
	public String submit(){
		return update();
	}
	
	public String update(){
		System.out.println("HALLA - TEXT: " + super.getText());
		
		Comment commentToUpdate = commentEJB.getById(super.getSelectedCommentId());
		commentToUpdate.setText(super.getText());
		//commentToUpdate.setCreated(Timestamp.from(Instant.now())); 
		
		boolean savedComment = commentEJB.saveComment(commentToUpdate);
		if(savedComment){	
			return "post-details.xhtml?faces-redirect=true&id=" + commentToUpdate.getPost().getId();
		}else{
			super.addFacesMessage("Could not update");
		}return "post-details.xhtml?faces-redirect=true&id=" + commentToUpdate.getPost().getId();
	}

}

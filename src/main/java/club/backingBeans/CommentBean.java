package club.backingBeans;

import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import club.DAO.Comment;
import club.DAO.Post;
import club.DAO.User;
import club.EJB.interfaces.LocalComment;
import club.backingBeans.user.LoginUserBean;


@Named(value="commentBean")
@RequestScoped
public class CommentBean extends BasicFrontendBean{

	private String text;
	private User author;
	private Post post;
	private LocalDateTime created;
	private int selectedCommentId;
	
	@EJB
	private LocalComment commentEJB;

	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;
	
	@PostConstruct
	public void init() {
		//redirectIfNotLoggedIn(); //TODO: fix redirect
		this.author = loginUserBean.getUser();
	}

	public CommentBean(){
	}
		
	
	public String updateComment(){
		System.out.println("HALLA TEXT: " + text + "AND ID: " + selectedCommentId);
		
		Comment commentToUpdate = commentEJB.getById(selectedCommentId);
		commentToUpdate.setText(text);
		//commentToUpdate.setCreated(Timestamp.from(Instant.now())); 
		
		boolean savedComment = commentEJB.saveComment(commentToUpdate);
		if(savedComment){	
			return "post-details.xhtml?faces-redirect=true&id=" + commentToUpdate.getPost().getId();
		}else{
			super.addFacesMessage("Could not update");
		}return "post-details.xhtml?faces-redirect=true&id=" + commentToUpdate.getPost().getId();
	}

	public String deleteComment(){
		System.out.println("DELETE FFS");
		Comment commentToDelete = commentEJB.getById(selectedCommentId);
		commentToDelete.setHidden(true);
		
		boolean deletedComment = commentEJB.saveComment(commentToDelete);
		if(deletedComment){
			System.out.println("DELETED COMMENT");
			return "post-details.xhtml?faces-redirect=true&id=" + commentToDelete.getPost().getId();
		}else{
			System.out.println("NOT DELETED COMMENT");
			super.addFacesMessage("Could not delete");
		}return "post-details.xhtml?faces-redirect=true&id=" + commentToDelete.getPost().getId();
	}
	
	public void setFieldFromSelectedComment(){
		Comment comment = commentEJB.getById(selectedCommentId);
		setText(comment.getText());
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public int getSelectedCommentId() {
		return selectedCommentId;
	}

	public void setSelectedCommentId(int selectedCommentId) {
		this.selectedCommentId = selectedCommentId;
	}

}

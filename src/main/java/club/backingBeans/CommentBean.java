package club.backingBeans;

import java.time.Instant;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.type.descriptor.java.PrimitiveByteArrayTypeDescriptor;

import java.sql.Timestamp;
import club.DAO.Comment;
import club.DAO.News;
import club.DAO.Post;
import club.DAO.User;
import club.EJB.interfaces.LocalComment;
import club.EJB.interfaces.LocalGenericCrud;
import club.backingBeans.user.LoginUserBean;
import club.exceptions.ValidateException;


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

	@Inject @Named(value="postGetterBean")
	private PostGetterBean postGetterBean;
	
	@PostConstruct
	public void init() {
		//redirectIfNotLoggedIn(); //TODO: fix redirect
		this.author = loginUserBean.getUser();
	}

	public CommentBean(){
	}
	
	public String submit() {
		return "";
	}
	
	public String saveComment() {
		
		System.out.println("..1");
		
		Comment comment = new Comment();		
		comment.setCreated(Timestamp.from(Instant.now()));
		comment.setText(text);
		comment.setUser(author);
		System.out.println("..2");
				
		News post1 = postGetterBean.getAsNews();
		comment.setPost(post1);
		
		System.out.println("..3");

		try {
			commentEJB.validateComment(comment);
			boolean isSaved = false;
			isSaved = commentEJB.saveComment(comment);
			if(isSaved) {
				text = null;
				return "post-details.xhtml?faces-redirect=true&id=" + post1.getId();

			}
			else {
				//TODO: how to handle this?
				return "post-details.xhtml?faces-redirect=true&id=" + post1.getId();

			}
		}
		catch(ValidateException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		
		return "post-details.xhtml?faces-redirect=true&id=" + post1.getId();
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
			return "post-details.xhtml?faces-redirect=true&id=" + commentToDelete.getPost().getId();
		}else{
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

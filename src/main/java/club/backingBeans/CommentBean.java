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
import java.sql.Timestamp;
import club.DAO.Comment;
import club.DAO.Post;
import club.DAO.User;
import club.EJB.interfaces.LocalComment;
import club.backingBeans.user.LoginUserBean;
import club.backingBeans.user.UserBean;
import exceptions.FormException;

@Named(value="comment")
@RequestScoped
public class CommentBean {

	private String text;
	private User author;
	private Post post;
	private LocalDateTime created;
	
	@EJB
	private LocalComment commentEJB;

	@Inject @Named("loginUser")
	private LoginUserBean loginUserBean;

	@Inject @Named(value="news")
	private NewsBean newsBean;

	@PostConstruct
	public void init() {
		//redirectIfNotLoggedIn(); //TODO: fix redirect
		this.author = loginUserBean.getUser();
	}

	public CommentBean(){
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
	
	public String saveComment() {
		
		Comment comment = new Comment();		
		comment.setCreated(Timestamp.from(Instant.now()));
		comment.setText(text);
		comment.setUser(author);
		comment.setPost(post);

		try {
			validatePost(comment);
			boolean isSaved = false;
			isSaved = commentEJB.saveComment(comment);
		}
		catch(FormException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		
		return "";
	}

	private void validatePost(Comment comment) throws FormException {
		if(comment.getPost()==null) throw new FormException("INTERNAL ERROR: no post");
		if(comment.getUser()==null) throw new FormException("INTERNAL ERROR: no user");
		
	}
		
}

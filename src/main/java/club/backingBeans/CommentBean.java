package club.backingBeans;

import java.time.Instant;
import java.time.LocalDateTime;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import club.DAO.Comment;
import club.DAO.News;
import club.DAO.Post;
import club.DAO.User;
import club.EJB.interfaces.LocalComment;
import club.backingBeans.user.LoginUserBean;
import club.backingBeans.user.UserProfileBean;
import club.exceptions.ValidateException;


@Named(value="commentBean")
@RequestScoped
public class CommentBean {

	private String text;
	private User author;
	private Post post;
	private LocalDateTime created;
	
	@EJB
	private LocalComment commentEJB;

	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;

	@Inject @Named(value="newsBean")
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
		System.out.println("setting post to post: " + post);
		this.post = post;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	public String saveComment() {
		System.out.println("In saveComment!");
		Comment comment = new Comment();		
		comment.setCreated(Timestamp.from(Instant.now()));
		comment.setText(text);
		comment.setUser(author);
		
		newsBean.useSelectedNews();
		News post1 = newsBean.getSelectedNews();
		System.out.println("post from newsBean is: " + post1);
		comment.setPost(post1);
		
		System.out.println("Comment is: " + comment);

		try {
			commentEJB.validateComment(comment);
			boolean isSaved = false;
			isSaved = commentEJB.saveComment(comment);
			if(isSaved) {
				text = null;
			}
			else {
				//TODO: how to handle this?
			}
		}
		catch(ValidateException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		
		return "";
	}


		
}

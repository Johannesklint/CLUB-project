package club.backingBeans;

import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import club.EJB.interfaces.LocalUser;
import club.backingBeans.user.UserBean;

@Named(value="comment")
@RequestScoped
public class CommentBean {

	private String text;
	private UserBean author;
	private NewsBean parentPost;
	private LocalDateTime created;
	
	@EJB
	private LocalUser userEJB;
	
	public CommentBean(){
	}
	
	public CommentBean(String text, UserBean author, NewsBean parentPost, LocalDateTime created) {
		this.text = text;
		this.author = author;
		this.parentPost = parentPost;
		this.created = created;
	}
	

	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public UserBean getAuthor() {
		return author;
	}
	public void setAuthor(UserBean author) {
		this.author = author;
	}
	public NewsBean getParentPost() {
		return parentPost;
	}
	public void setParentPost(NewsBean parentPost) {
		this.parentPost = parentPost;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	
	
	
}

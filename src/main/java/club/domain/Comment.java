package club.domain;

import java.time.LocalDateTime;

public class Comment {

	private String text;
	private UserBean author;
	private Post parentPost;
	private LocalDateTime created;
	
	public Comment(String text, UserBean author, Post parentPost, LocalDateTime created) {
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
	public Post getParentPost() {
		return parentPost;
	}
	public void setParentPost(Post parentPost) {
		this.parentPost = parentPost;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	
	
	
}

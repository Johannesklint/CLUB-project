package club.backingBeans.comment;

import java.time.LocalDateTime;
import club.DAO.post.Post;
import club.DAO.user.User;
import club.backingBeans.BasicFrontendBean;

public abstract class CommentBean extends BasicFrontendBean {

	private String text;
	private User author;
	private Post post;
	private LocalDateTime created;
	private int selectedCommentId;
		
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

	public int getSelectedCommentId() {
		return selectedCommentId;
	}
}

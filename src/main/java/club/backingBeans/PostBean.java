package club.backingBeans;

import java.sql.Timestamp;
import java.time.Instant;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import club.DAO.Post;
import club.DAO.User;
import club.EJB.interfaces.LocalPost;

public abstract class PostBean<T extends Post> extends BasicFrontendBean {
	
	@EJB
	private LocalPost postEJB;
	
	private String text;
	private String title;
	private User author;
	private int id;
	
	
	@PostConstruct
	private void init(){
		System.out.println("In PostBean init");
	}
	
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	Post save() {
		Post post = getFromFields();
		return postEJB.save(post);
	}
	
	Post update() {
		Post post = updateFromFields();
		return postEJB.save(post);
	}
	
	/**
	 * Handles author, title, text, hidden, and created
	 * @param post to update
	 * @return
	 */
	public Post getBasicPostEntityFromFields(Post post) {
		post.setAuthor(this.author);
		post.setTitle(this.title);
		post.setText(this.text);
		post.setHidden(false);
		post.setCreated(Timestamp.from(Instant.now()));
		return post;
	}
	
	
	/**
	 * Will be run when {@link #save()} is executed.
	 * @return 
	 */
	public abstract T getFromFields();
	/**
	 * Will be run when {@link #update()} is executed.
	 * @return 
	 */
	public abstract T updateFromFields();
	

}

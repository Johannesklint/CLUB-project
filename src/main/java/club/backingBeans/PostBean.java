package club.backingBeans;

import java.sql.Timestamp;
import java.time.Instant;

import club.DAO.Post;
import club.DAO.User;
import club.EJB.PostEJB;

public abstract class PostBean<T extends Post> extends BasicFrontendBean {
	
	PostEJB postEJB;
	
	private String text;
	private String title;
	private User author;
	private int id;
	
	
	
	void save() {
		Post post = getFromFields();
		postEJB.save(post);
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

}

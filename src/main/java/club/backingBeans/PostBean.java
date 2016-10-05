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
	
	public Post getById(int id) {
		return postEJB.getById(id);
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

	public String createAndRedirect(){ // TODO: naming standard
		System.out.println("creating news..");

		Post savedPost = save();
		System.out.println("saved news is: " + savedPost);
		
		if(savedPost != null) {
			System.out.println("Saved news");
			return "post-details.xhtml?faces-redirect=true&id=" + savedPost.getId();
		} else {
			System.out.println("Failed to save news");
			return "";
		}
	}

	
	String updateAndGetRedirect(){ // TODO: rename
		System.out.println("inne i update news " + getTitle());
		
		Post savedPost = update();
			if(savedPost != null){
				return "post-details.xhtml?faces-redirect=true&id=" + savedPost.getId();
			}
		return "";
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
	
	private Post update() {
		Post post = updateFromFields();
		return postEJB.save(post);
	}

}

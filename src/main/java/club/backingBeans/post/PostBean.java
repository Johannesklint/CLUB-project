package club.backingBeans.post;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import club.DAO.post.Post;
import club.DAO.user.User;
import club.EJB.interfaces.LocalPost;
import club.backingBeans.BasicFrontendBean;

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

	protected Post save() {
		Post post = getFromFields();
		return postEJB.save(post);
	}

	public String createAndRedirect(){
		Post savedPost = save();
		if(savedPost != null) {
			return "post-details.xhtml?faces-redirect=true&id=" + savedPost.getId();
		} else {
			return "";
		}
	}

	public String deleteAndRedirect() {
		postEJB.delete(id);
		return "home";
	}
	
	protected String updateAndGetRedirect(){
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

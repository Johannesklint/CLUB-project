package club.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Post {

	private String description;
	private String title;
	private UserBean author;
	private LocalDateTime created;
	private List<UserBean> followers;
	
	
	public Post(String description, String title, UserBean author, LocalDateTime created, List<UserBean> followers) {
		this.description = description;
		this.title = title;
		this.author = author;
		this.created = created;
		this.followers = followers;
	}
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public UserBean getAuthor() {
		return author;
	}
	public void setAuthor(UserBean author) {
		this.author = author;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public List<UserBean> getFollowers() {
		return followers;
	}
	public void setFollowers(List<UserBean> followers) {
		this.followers = followers;
	}
	
	
	
	
	
}

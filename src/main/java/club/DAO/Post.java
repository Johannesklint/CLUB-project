package club.DAO;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="post")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="post_type", discriminatorType=DiscriminatorType.STRING)
@NamedQueries({
    @NamedQuery(name="Post.findAll", query="SELECT p FROM Post p")
}) 
public abstract class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String text;
	private String title;
	private User author;
	
	private Timestamp created;
//	@OneToMany(targetEntity=User.class, mappedBy="comments")
//	private List<User> followers;

	@OneToMany(mappedBy="post")
	private List<Comment> comments;
	
	public Post() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	
//	public List<User> getFollowers() {
//		return followers;
//	}
//	public void setFollowers(List<User> followers) {
//		this.followers = followers;
//	}
	
	
}

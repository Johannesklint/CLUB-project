package club.DAO.comment;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import club.DAO.post.Post;
import club.DAO.user.User;

import java.sql.Timestamp;


/**
 * The persistent class for the comment database table.
 * 
 */
@Entity
@Table(name="comment")
@NamedQueries( { 
	@NamedQuery(name="Comment.findAll", query="SELECT c FROM Comment c"),
	@NamedQuery(name="Comment.findAllByPostId", query="SELECT c FROM Comment c WHERE c.post.id = :postId")
})
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false)
	private Timestamp created;

	@Column(name="last_updated")
	private Timestamp lastUpdated;

	@Column(nullable=false, length=140)
	private String text;

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="author_id", nullable=false)
	private User user;

	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="post_id", nullable=false)
	private Post post;
	
	@Column(name="hidden", nullable=false)
	private boolean hidden;

	
	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public Comment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonIgnore
	public Post getPost() {
		return this.post;
	}
	@JsonIgnore
	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", created=" + created + ", text=" + text + ", user=" + user + ", post=" + post
				+ "]";
	}

}
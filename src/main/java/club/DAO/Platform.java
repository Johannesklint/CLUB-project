package club.DAO;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the platform database table.
 * 
 */
@Entity
@Table(name="platform")
@NamedQuery(name="Platform.findAll", query="SELECT p FROM Platform p")
public class Platform implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	@Lob
	@Column(name="terms_and_condition")
	private String termsAndCondition;

	@Column(length=45)
	private String title;

	//bi-directional many-to-one association to Theme
	@ManyToOne( cascade=CascadeType.MERGE)
	@JoinColumn(name="theme_id")
	private Theme theme;

	public Platform() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTermsAndCondition() {
		return this.termsAndCondition;
	}

	public void setTermsAndCondition(String termsAndCondition) {
		this.termsAndCondition = termsAndCondition;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Theme getTheme() {
		return this.theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

}
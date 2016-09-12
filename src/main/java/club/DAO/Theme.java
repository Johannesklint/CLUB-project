package club.DAO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the theme database table.
 * 
 */
@Entity
@Table(name="theme")
@NamedQuery(name="Theme.findAll", query="SELECT t FROM Theme t")
public class Theme implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="primary_color_hex", nullable=false, length=7)
	private String primaryColorHex;

	@Column(name="secondary_color_hex", nullable=false, length=7)
	private String secondaryColorHex;

	//bi-directional many-to-one association to Platform
	@OneToMany(mappedBy="theme")
	private List<Platform> platforms;

	public Theme() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrimaryColorHex() {
		return this.primaryColorHex;
	}

	public void setPrimaryColorHex(String primaryColorHex) {
		this.primaryColorHex = primaryColorHex;
	}

	public String getSecondaryColorHex() {
		return this.secondaryColorHex;
	}

	public void setSecondaryColorHex(String secondaryColorHex) {
		this.secondaryColorHex = secondaryColorHex;
	}

	public List<Platform> getPlatforms() {
		return this.platforms;
	}

	public void setPlatforms(List<Platform> platforms) {
		this.platforms = platforms;
	}

	public Platform addPlatform(Platform platform) {
		getPlatforms().add(platform);
		platform.setTheme(this);

		return platform;
	}

	public Platform removePlatform(Platform platform) {
		getPlatforms().remove(platform);
		platform.setTheme(null);

		return platform;
	}

}
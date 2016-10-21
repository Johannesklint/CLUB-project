package club.backingBeans.platform;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.platform.Platform;
import club.DAO.platform.Theme;
import club.EJB.interfaces.LocalPlatform;
import club.backingBeans.BasicFrontendBean;

public abstract class PlatformBean extends BasicFrontendBean {
	
	@PostConstruct
	public void init() {
		System.out.println("IN platform bean init!!");
		setPlatformBeanFieldsFromDb();
	}
	
	public static final String DEFAULT_TITLE = "Default Title";
	public static final String DEFAULT_DESCRIPTION = "Default Description";

	private String title;
	private String description;
	private String termsAndConditions;
	
	@Inject @Named(value="themeBean")
	private ThemeBean theme;
	
	@EJB
	private LocalPlatform platformEJB;
	
	public PlatformBean() {
	}
	
	public Platform getPlatformById(){
		Platform platform = platformEJB.getById(1);
		return platform;
	}
	
	public String getTitle() {
		return title != null ? title : DEFAULT_TITLE;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description != null ? description : DEFAULT_DESCRIPTION;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public ThemeBean getTheme() {
		return theme;
	}
	public void setTheme(ThemeBean theme) {
		this.theme = theme;
	}
	
	
	protected void setPlatformBeanFieldsFromDb() {
		Platform platform = getPlatformById();
		System.out.println("init platform: " + platform.getTitle());
		
		// set PlatformBean fields
		
		this.setTitle(platform.getTitle());
		this.setDescription(platform.getDescription());
		this.setTermsAndConditions(platform.getTermsAndCondition());
		
		// set ThemeBean fields
		
		Theme theme = platform.getTheme();
		this.theme.setPrimaryColorHEX(theme.getPrimaryColorHex());
		this.theme.setSecondaryColorHEX(theme.getSecondaryColorHex());
	}
	
	
}

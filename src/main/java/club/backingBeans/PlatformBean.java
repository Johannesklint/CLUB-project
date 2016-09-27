package club.backingBeans;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.Platform;
import club.DAO.Theme;
import club.EJB.interfaces.LocalPlatform;
import club.backingBeans.user.UserBean;


@Named("platform")
@Startup
@Singleton
public class PlatformBean {
	
	
	@PostConstruct
	public void init() {
		setPlatformBeanFieldsFromDb();
	}
	
	public static final String DEFAULT_TITLE = "Default Title";
	public static final String DEFAULT_DESCRIPTION = "Default Description";

	private String title;
	private String description;
	private String termsAndConditions;
	
	@Inject @Named(value="theme")
	private ThemeBean theme;
	
	@EJB
	private LocalPlatform platformEJB;
	
	public PlatformBean() {
	}
	
	public Platform getPlatformById(){
		Platform platform = platformEJB.getPlatformById(1);
		return platform;
	}
	
	public String savePlatform(){

		System.out.println("before saveplatform: " + theme == null);
		
		Platform platform = new Platform();
		platform.setId(1);
		platform.setTitle(title);
		platform.setDescription(description);
		platform.setTermsAndCondition(termsAndConditions);
		platform.setTheme(theme.createThemeFromBean());
		
		System.out.println("i saveplatform" + theme == null);
		
		if(platformEJB.savePlatform(platform)){
			setPlatformBeanFieldsFromDb();
		}
		
		return "";
		
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
	
	
	private void setPlatformBeanFieldsFromDb() {
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

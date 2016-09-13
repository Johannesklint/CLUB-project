package club.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.Platform;
import club.EJB.interfaces.LocalPlatform;


@Named("platform")
@RequestScoped // TODO: more permanent scope. 
public class PlatformBean {
	
	public static final String DEFAULT_TITLE = "Default Title";
	public static final String DEFAULT_DESCRIPTION = "Default Description";

	private String title;
	private String description;
	private String termsAndConditions;
	private List<UserBean> users;
	
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
			this.title = null;
			this.description = null;
			this.termsAndConditions = null;
			this.theme = null;
			
		}
		
		return "index";
		
	}
	
	
	public List<UserBean> getUnapprovedUsers() {
		return users.stream()
			.filter(user -> !user.isApproved())
			.collect(Collectors.toList());
	}
	
	public List<UserBean> getApprovedUsers() {
		return users.stream()
			.filter(user -> user.isApproved())
			.collect(Collectors.toList());
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
	public List<UserBean> getUsers() {
		return users;
	}
	public void setUsers(List<UserBean> users) {
		this.users = users;
	}
	public ThemeBean getTheme() {
		return theme;
	}
	public void setTheme(ThemeBean theme) {
		this.theme = theme;
	}
	
	
}

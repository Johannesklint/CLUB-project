package club.domain;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named("platform")
@RequestScoped // TODO: more permanent scope. 
public class PlatformBean {
	
	public static final String DEFAULT_TITLE = "Default Title";
	public static final String DEFAULT_DESCRIPTION = "Default Description";

	private String title;
	private String description;
	private String termsAndConditions;
	private List<UserBean> users;
	private ColorTheme colorTheme;
	
	
	
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
	public ColorTheme getColorTheme() {
		return colorTheme;
	}
	public void setColorTheme(ColorTheme colorTheme) {
		this.colorTheme = colorTheme;
	}
	
	
}

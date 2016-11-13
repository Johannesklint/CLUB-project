package club.backingBeans.platform;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.platform.Platform;
import club.EJB.interfaces.LocalPlatform;

@RequestScoped
@Named(value="configurePlatformBean")
public class ConfigurePlatformBean extends PlatformBean {
	
	@EJB
	private LocalPlatform platformEJB;
	
	@Inject @Named(value="platformBean")
	private DisplayPlatformBean displayPlatformBean;
	
	
	public String updatePlatform(){
		
		Platform platform = new Platform();
		platform.setId(1);
		platform.setTitle(super.getTitle());
		platform.setDescription(super.getDescription());
		platform.setTermsAndCondition(super.getTermsAndConditions());
		platform.setTheme(super.getTheme().createThemeFromBean());
		
		platformEJB.update(platform);
		super.setPlatformBeanFieldsFromDb();

		
		displayPlatformBean.setTitle(super.getTitle());
		displayPlatformBean.setDescription(super.getDescription());
		displayPlatformBean.setTermsAndConditions(super.getTermsAndConditions());
		
		
		return "configure-platform";
		
	}	

}

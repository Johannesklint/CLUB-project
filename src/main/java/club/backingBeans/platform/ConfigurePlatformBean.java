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
		
		//TODO: think platform is a bit hack. saveing platform to db, update this object by database, and last set dipsplay platform fields from this object. But not Theme, it works somehow anyway
		
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

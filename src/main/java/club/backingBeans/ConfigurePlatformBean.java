package club.backingBeans;

import javax.inject.Inject;
import javax.inject.Named;

import club.backingBeans.user.LoginUserBean;

@Named(value="configurePlatformBean")
public class ConfigurePlatformBean extends PlatformBean {

	@Inject @Named(value="loginUserBean")
	LoginUserBean loginUserBean;

}

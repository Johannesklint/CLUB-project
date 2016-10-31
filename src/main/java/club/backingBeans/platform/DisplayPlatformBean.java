package club.backingBeans.platform;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;

/**
 *  Class needed because this one has to have Singelton scope <br>
 *  while {@link ConfigurePlatformBean}  requires RequestScope
 *  
 */
@Startup
@Singleton
@Named("platformBean")
public class DisplayPlatformBean extends PlatformBean {
		
}

package club.backingBeans.platform;

import javax.ejb.Singleton;
import javax.inject.Named;

import club.DAO.platform.Theme;

@Named(value = "themeBean")
@Singleton
public class ThemeBean {
	
	private String primaryColorHEX;
	private String secondaryColorHEX;
	
	public ThemeBean() {
	}
	
	public Theme createThemeFromBean(){
		Theme theme = new Theme();
		theme.setId(1);
		theme.setPrimaryColorHex(primaryColorHEX);
		theme.setSecondaryColorHex(secondaryColorHEX);
		
		return theme;
	}
	
	
	public String getPrimaryColorHEX() {
		return primaryColorHEX;
	}
	public void setPrimaryColorHEX(String primaryColorHEX) {
		this.primaryColorHEX = primaryColorHEX;
	}
	public String getSecondaryColorHEX() {
		return secondaryColorHEX;
	}
	public void setSecondaryColorHEX(String secondaryColorHEX) {
		this.secondaryColorHEX = secondaryColorHEX;
	}
	
	public String getPrimaryColorHEXwithHash() {
		return "#"+getPrimaryColorHEX();
	}

	public String getSecondaryColorHEXwithHash() {
		return "#"+getSecondaryColorHEX();		
	}

}

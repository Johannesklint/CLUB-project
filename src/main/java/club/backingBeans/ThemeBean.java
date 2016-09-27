package club.backingBeans;

import javax.ejb.Singleton;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import club.DAO.Theme;

@Named(value = "themeBean")
@Singleton
public class ThemeBean {
	
	private String primaryColorHEX;
	private String secondaryColorHEX;
	
	public ThemeBean() {
		this.primaryColorHEX = "#B312C7";
		this.secondaryColorHEX = "#1695A3";
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
	
	
	
	

}

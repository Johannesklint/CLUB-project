package club.domain;

public class ColorTheme {
	
	private String primaryColorHEX;
	private String secondaryColorHEX;
	
	public ColorTheme() {
		this.primaryColorHEX = "#B312C7";
		this.secondaryColorHEX = "#1695A3";
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

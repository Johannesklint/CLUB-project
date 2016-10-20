package club.EJB;

import javax.ejb.EJB;

import club.DAO.platform.Theme;
import club.DAO.platform.ThemeDAO;
import club.EJB.interfaces.LocalTheme;

public class ThemeEJB implements LocalTheme{
	
	@EJB
	ThemeDAO themeDao;

	@Override
	public Theme getThemeById(int id) {
		return themeDao.getUserById(id);
	}

}

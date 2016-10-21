package club.EJB;

import java.util.List;

import javax.ejb.EJB;

import club.DAO.platform.Theme;
import club.DAO.platform.ThemeDAO;
import club.EJB.interfaces.LocalTheme;

public class ThemeEJB implements LocalTheme{
	
	@EJB
	ThemeDAO themeDao;

	@Override
	public Theme getById(int id) {
		return themeDao.getById(id);
	}

	@Override
	public Theme update(Theme entity) {
		// N/A
		return null;
	}

	@Override
	public Theme save(Theme entity) {
		// N/A
		return null;
	}

	@Override
	public boolean delete(int id) {
		// N/A
		return false;
	}

	@Override
	public List<Theme> getAll() {
		// N/A
		return null;
	}

}

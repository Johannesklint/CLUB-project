package club.EJB;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.platform.Platform;
import club.DAO.platform.PlatformDAO;
import club.EJB.interfaces.LocalPlatform;

@Stateless
public class PlatformEJB implements LocalPlatform{

	@EJB
	private PlatformDAO platformDAO;
	
	@Override
	public Platform save(Platform platform) {	
		return platformDAO.save(platform);
	}

	@Override
	public Platform getById(int id) {
		return platformDAO.getById(id);
	}

	@Override
	public Platform update(Platform entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Platform> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

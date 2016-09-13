package club.EJB;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import club.DAO.Platform;
import club.DAO.PlatformDAO;
import club.EJB.interfaces.LocalPlatform;

@Stateless
public class PlatformEJB implements LocalPlatform{

	@EJB
	private PlatformDAO platformDAO;
	
	@Override
	public boolean savePlatform(Platform platform) {	
		return platformDAO.saveToDB(platform);
	}

	@Override
	public Platform getPlatformById(int id) {
		return platformDAO.getFromDBById(id);
	}

}

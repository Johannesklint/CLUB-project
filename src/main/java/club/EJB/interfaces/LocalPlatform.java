package club.EJB.interfaces;

import javax.ejb.Local;

import club.DAO.Platform;


@Local
public interface LocalPlatform {
	boolean savePlatform(Platform platform);
	Platform getPlatformById(int id);
}


package club.EJB.interfaces;

import javax.ejb.Local;

import club.DAO.platform.Platform;


@Local
public interface LocalPlatform extends LocalGenericCrud<Platform> {
	
}


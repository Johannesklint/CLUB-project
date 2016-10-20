package club.DAO.platform;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import club.DAO.GenericCrudDao;

@Stateful
public class PlatformDAO extends GenericCrudDao<Platform> {


}

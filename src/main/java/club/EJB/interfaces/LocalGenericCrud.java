package club.EJB.interfaces;

import java.util.List;

import javax.ejb.Local;

/**
 * 
 * Contract to provide the basic CRUD methods
 *
 * @param <T>
 */
@Local
public interface LocalGenericCrud<T> {
	
	T update(T entity);
	
	T save(T entity);

	boolean delete(int id);
	
	List<T> getAll();

	T getById(int id);

}

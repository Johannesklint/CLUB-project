package club.DAO;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * Provides basic CRUD operations for any entity of type T. <br>
 * Note that this class has an EntityManager, so any child class <br>
 * can use that same instance, for example through:  super.entityManager 
 *
 * @param <T>
 */
public abstract class GenericCrudDao<T> {
	
	@SuppressWarnings("rawtypes")
	// NOTE! Don't access this directly, go through #getProvidedGenericClass()
	private Class daoEntityClass; 
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public T save(T entity) {
		System.out.println("Generic save");
		return entityManager.merge(entity);
	}

	@SuppressWarnings("unchecked")
	public T getById(int id) {
		System.out.println("Generic getById");
		return (T) entityManager.find(getProvidedGenericClass(), id);
	}

	@SuppressWarnings("unchecked")
	public boolean delete(int id) {
		T entity = (T) entityManager.find(getProvidedGenericClass(), id);
		if(entity != null){
			entityManager.remove(entity);
			return true;
		}
		return false;
	}

	/**
	 * NOTE: method assumes presence of NamedQuery with name {className}.findAll
	 * @return
	 */
	@SuppressWarnings("unchecked")
	List<T> getAll() {
		System.out.println("Generic getAll");
		String className = getProvidedGenericClass().getSimpleName();
		System.out.println("Generic getAll. className is: " + className);
		Query query = entityManager.createNamedQuery(className + ".findAll");
		List<T> entities = (List<T>)query.getResultList();				
		return entities;
	}

	/**
	 * Since T.getClass() doesn't work, this is a common work-around to get the generic class.
	 * @return a class of same type as T, in "... extends GenericCrudDao&lt;T&gt;"
	 */
	@SuppressWarnings("rawtypes")
	private Class getProvidedGenericClass() {
		// get lazily. only do the real work once
		if(daoEntityClass != null) return daoEntityClass;
		
		// set the field 
		this.daoEntityClass = ((Class) ((ParameterizedType) getClass()
		        .getGenericSuperclass()).getActualTypeArguments()[0]);
		
		return this.daoEntityClass;
	}

}

package club.resource;

import java.util.Arrays;
import java.util.List;

/**
 * For use with "self" and other links when returning entity as a REST resource
 */
public class RESTLinkable<T> {
	private T entity; 
	private List<Link> links;
	
	public RESTLinkable(T entity, List<Link> links) {
		this.entity = entity;
		this.links = links;
	}
	
	public RESTLinkable(T entity, Link link) {
		this(entity, Arrays.asList(link));
	}
	
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	
}

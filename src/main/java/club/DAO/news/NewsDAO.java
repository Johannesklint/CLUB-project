package club.DAO.news;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateful;

import club.DAO.GenericCrudDao;

@Stateful
public class NewsDAO extends GenericCrudDao<News> {
	
	public List<News> getAll(boolean includeHidden) {
		return super.getAll()
			.stream()
			.filter(post -> post instanceof News)
			.filter( post -> !post.getHidden() || includeHidden)
			.map(post -> (News) post) // cast to please the compiler
			.collect(Collectors.toList());
	}
}

package club.DAO.post;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateful;

import club.DAO.GenericCrudDao;

@Stateful
public class PostDAO extends GenericCrudDao<Post> {

	public List<Post> getAll(boolean includeHidden) {
		return super.getAll()
			.stream()
			.filter(post -> post instanceof Post)
			.filter( post -> !post.getHidden() || includeHidden)
			.map(post -> (Post) post) // cast to please the compiler
			.collect(Collectors.toList());
	}

}

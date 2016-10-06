package club.DAO;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateful;

@Stateful
public class PostDAO extends GenericCrudDao<Post> {

	public List<News> getAllNews(boolean includeHidden) {
		return super.getAll(includeHidden)
			.stream()
			.filter(post -> post instanceof News)
			.filter( post -> !post.getHidden())
			.map(news -> (News) news) // cast to please the compiler
			.collect(Collectors.toList());
	}
	
	public List<Event> getAllEvents(boolean includeHidden) {
		return super.getAll()
			.stream()
			.filter(post -> post instanceof Event)
			.filter( post -> !post.getHidden())
			.map(post -> (Event) post) // cast to please the compiler
			.collect(Collectors.toList());
	}

	public List<Post> getAllPosts(boolean includeHidden) {
		return super.getAll()
			.stream()
			.filter(post -> post instanceof Post)
			.filter( post -> !post.getHidden())
			.map(post -> (Post) post) // cast to please the compiler
			.collect(Collectors.toList());
	}

}

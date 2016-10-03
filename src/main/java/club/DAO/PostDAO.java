package club.DAO;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateful;

@Stateful
public class PostDAO extends GenericCrudDao<Post> {

	public List<News> getAllNews() {
		return super.getAll()
			.stream()
			.filter(post -> post instanceof News)
			.map(news -> (News) news) // cast to please the compiler
			.collect(Collectors.toList());
	}
	
	public List<Event> getAllEvents() {
		return super.getAll()
			.stream()
			.filter(post -> post instanceof Event)
			.map(post -> (Event) post) // cast to please the compiler
			.collect(Collectors.toList());
	}
		
}

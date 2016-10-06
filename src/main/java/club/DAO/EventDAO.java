package club.DAO;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateful;

@Stateful
public class EventDAO extends GenericCrudDao<Event>{
	
	public List<Event> getAll(boolean includeHidden) {
		return super.getAll()
			.stream()
			.filter(post -> post instanceof Event)
			.filter( post -> !post.getHidden() || includeHidden)
			.map(post -> (Event) post) // cast to please the compiler
			.collect(Collectors.toList());
	}


}

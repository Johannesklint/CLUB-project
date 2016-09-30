package club.DAO;

import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateful;

@Stateful
public class EventDAO extends GenericCrudDao<Event>{

	@Override
	public List<Event> getAll() {
		List<Event> events = super.getAll();
		//TODO: since both Events and News have same 'do not show if' statement, this statement should be moved to Post somehow
		return events.stream()
				.filter( e -> !e.getHidden())
				.collect(Collectors.toList());
	}

}

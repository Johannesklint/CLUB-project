package club.DAO;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateful;

@Stateful
public class NewsDao extends GenericCrudDao<News> {
	
	@Override
	public List<News> getAll() {
		List<News> news = super.getAll();
		//TODO: since both Events and News have same 'do not show if' statement, this statement should be moved to Post somehow
		return news.stream()
				.filter(_news -> !_news.getHidden())
				.collect(Collectors.toList());
	}
	
	

}

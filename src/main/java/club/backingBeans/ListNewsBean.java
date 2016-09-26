package club.backingBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import club.DAO.News;
import club.EJB.interfaces.LocalNews;

@Named(value="listNews")
@RequestScoped
public class ListNewsBean {

	@EJB
	private LocalNews newsEJB;
	
	private boolean hasCalledGetAll = false;
	
	public List<News> getAll(){
		hasCalledGetAll = true;
		return newsEJB.getAll();
	}
	
	public Integer getCommentLimit() {
		return hasCalledGetAll ? 3 : null;
	}

}

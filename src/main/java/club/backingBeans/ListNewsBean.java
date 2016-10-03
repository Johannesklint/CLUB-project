package club.backingBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.News;
import club.EJB.interfaces.LocalNews;
import club.backingBeans.user.LoginUserBean;

@Named(value="listNewsBean")
@RequestScoped
public class ListNewsBean extends BasicFrontendBean {
	
	@Inject @Named(value="loginUserBean")
	LoginUserBean loginUserBean;

	@EJB
	private LocalNews newsEJB;
	
	@PostConstruct
	public void init() {
		super.redirectIfNotLoggedIn(loginUserBean);
	}
	
	public List<News> getAll(){
		return newsEJB.getAll();
	}

}

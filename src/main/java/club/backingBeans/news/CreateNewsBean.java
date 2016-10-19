package club.backingBeans.news;

import java.sql.Timestamp;
import java.time.Instant;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.News;
import club.backingBeans.NewsBean;
import club.backingBeans.user.LoginUserBean;

@RequestScoped
@Named(value="createNewsBean")
public class CreateNewsBean extends NewsBean {
	

	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;
		
	public String submit() {
		return super.createAndRedirect();
	}
	
	@PostConstruct
	public void init() {
		System.out.println("In CreateNewsBean init()");
	}

	@Override
	public News updateFromFields() {
		
		return null;
	}
	
	@Override
	public News getFromFields() {
		News news = new News();
		news.setAuthor(super.getAuthor());
		news.setTitle(super.getTitle());
		news.setText(super.getText());
		news.setHidden(false);
		news.setCreated(Timestamp.from(Instant.now()));
		return news;
	}

}

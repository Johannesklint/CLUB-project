package club.backingBeans.news;

import java.sql.Timestamp;
import java.time.Instant;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.news.News;
import club.DAO.user.User;
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
	}

	@Override
	public News updateFromFields() {		
		return null;
	}

	@Override
	public User getAuthor() {
		return loginUserBean.getUser();
	}

	@Override
	public News getFromFields() {
		News news = new News();
		news.setAuthor(this.getAuthor());  // note: must use this instead of super since getAuthor is overriden
		news.setTitle(super.getTitle());
		news.setText(super.getText());
		news.setHidden(false);
		news.setCreated(Timestamp.from(Instant.now()));
		return news;
	}

}

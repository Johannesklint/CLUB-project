package club.backingBeans;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.News;
import club.DAO.User;
import club.EJB.interfaces.LocalNews;
import club.backingBeans.user.LoginUserBean;

@Named(value="news")
@RequestScoped
@Startup
public class NewsBean {

	private String text;
	private String title;
	private User author;
	
	@Inject @Named("loginUser")
	private LoginUserBean loginUserBean;
	
	@EJB
	private LocalNews newsEJB;
	
	public NewsBean() {
	}
	
	@PostConstruct
	public void init() {
		redirectIfNotLoggedIn();
		this.author = loginUserBean.getUser();
	}
	
	
	public String createNews(){
		
		redirectIfNotLoggedIn();
		
		News news = new News();
		news.setAuthor(this.author);
		news.setTitle(this.title);
		news.setText(this.text);
		news.setCreated(LocalDateTime.now());
		
		if(newsEJB.saveNews(news)) {
			System.out.println("Saved news");
			clearBeanFields();
		} else {
			System.out.println("Failed to save news");
		}
		
		return "create-news.xhtml";
	}
	
	public List<News> getAll(){
		return newsEJB.getAll();
	}

	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	
	private void clearBeanFields() {
		this.title = null;
		this.text = null;
	}
	
	private void redirectIfNotLoggedIn() {
		setAuthorFromUserLoginBean();
		
		if(this.author != null) return; // everything is ok!
		
		
		try {
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.redirect(context.getRequestContextPath() + "/faces/login-index.xhtml");				
		} catch(Exception ex) {
			// YOLO
		}
	}
	
	private void setAuthorFromUserLoginBean(){
		this.author = loginUserBean.getUser();
		
	}
	
}

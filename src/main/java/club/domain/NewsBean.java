package club.domain;

import java.io.IOException;
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

@Named(value="news")
@RequestScoped
@Startup
public class NewsBean {

	private String description;
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
		this.author = loginUserBean.getUser();
		if(author == null) {
			try {
				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				context.redirect(context.getRequestContextPath() + "/faces/login-index.xhtml");				
			} catch(Exception ex) {
				// YOLO
			}
		}
	}
	
	
	public String createNews(){
		
		News news = new News();
		news.setAuthor(this.author);
		news.setTitle(this.title);
		news.setDescription(this.description);
		news.setCreated(LocalDateTime.now());
		
		if(newsEJB.saveNews(news)) {
			System.out.println("Saved news");
		} else {
			System.out.println("Failed to save news");
		}
		
		return "create-news.xhtml";
	}

	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	
}

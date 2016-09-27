package club.backingBeans;

import java.sql.Timestamp;
import java.time.Instant;
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
	private int id;
	private int selectedNewsId;
	private News selectedNews;
	
	
	@Inject @Named("loginUser")
	private LoginUserBean loginUserBean;
	
	@EJB
	private LocalNews newsEJB;
	
	public NewsBean() {
		System.out.println("Creating new NewsBean");
	}
	
	@PostConstruct
	public void init() {
		redirectIfNotLoggedIn();
		this.author = loginUserBean.getUser();
	}
	
	
	public String createNews(){ // TODO: naming standard
		
		redirectIfNotLoggedIn();
		
		News news = new News();
		news.setAuthor(this.author);
		news.setTitle(this.title);
		news.setText(this.text);
		news.setCreated(Timestamp.from(Instant.now()));
		
		if(newsEJB.saveNews(news)) { // TODO: return saved entity
			System.out.println("Saved news");
			clearBeanFields();
		} else {
			System.out.println("Failed to save news");
		}
		
		return "create-news.xhtml"; // TODO:  redirect to post-details.xhtml?id= + saved.id
	}
	

	public String updateNews(){
		
		System.out.println("inne i update news " + title);
		
		redirectIfNotLoggedIn();
		
		News newsToUpdate = newsEJB.getNewsById(selectedNewsId);
		newsToUpdate.setTitle(title);
		newsToUpdate.setText(text);
		
			if(newsEJB.saveNews(newsToUpdate)){
				return "home-index";
			}
		return "create-news.xhtml";
	} 
	
	public String deleteNews(){
		if(newsEJB.deleteNews(selectedNewsId)){
			return "home-index";
		}else{
			return "";
		}
	}
	
	public String setFieldFromSelectedNews(){
		News news = newsEJB.getNewsById(selectedNewsId);
		setAuthor(news.getAuthor());
		setText(news.getText());
		setTitle(news.getTitle());
		
		return "update-news-index";
		
	}
		

	public void useSelectedNews(){
		selectedNews = newsEJB.getNewsById(selectedNewsId);
		System.out.println("getting news with title: " + selectedNews.getTitle());
	}

	
	public int getSelectedNewsId() {
		return selectedNewsId;
	}

	public void setSelectedNewsId(int selectedNewsId) {
		System.out.println("Setting selectedNews to: " + selectedNewsId);
		this.selectedNewsId = selectedNewsId;
	}

	public List<News> getAll(){
		return newsEJB.getAll();
	}

	public News getSelectedNews() {
		return selectedNews;
	}

	public void setSelectedNews(News selectedNews) {
		this.selectedNews = selectedNews;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public Integer getCommentLimit() {
		return null;
	}
	
}

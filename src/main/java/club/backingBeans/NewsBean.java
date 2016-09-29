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


@Named(value="newsBean")
@RequestScoped
public class NewsBean extends BasicFrontendBean {

	private String text;
	private String title;
	private User author;
	private int id;
	private int selectedNewsId;
	private News selectedNews;
	
	
	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;
	
	@EJB
	private LocalNews newsEJB;
	
	public NewsBean() {
		System.out.println("Creating new NewsBean");
	}
	
	@PostConstruct
	public void init() {
		super.redirectIfNotLoggedIn(loginUserBean);
		this.author = loginUserBean.getUser();
	}
	
	
	public String create(){ // TODO: naming standard
		System.out.println("creating news..");

		News newsToSave = getNewsEntityFromFields();
		
		News savedNews = newsEJB.saveNews(newsToSave);
		
		if(savedNews != null) {
			System.out.println("Saved news");
			return "post-details.xhtml?faces-redirect=true&id=" + savedNews.getId();
		} else {
			System.out.println("Failed to save news");
			return "";
		}
	}
	

	public String update(){
		System.out.println("inne i update news " + title);
		
		News newsToUpdate = newsEJB.getNewsById(selectedNewsId);
		newsToUpdate.setTitle(title);
		newsToUpdate.setText(text);
		
		News savedNews = newsEJB.saveNews(newsToUpdate);
			if(savedNews != null){
				return "post-details.xhtml?faces-redirect=true&id=" + savedNews.getId();
			}
		return "";
	} 
	
	public String deleteNews(){

		News newsToUpdate = newsEJB.getNewsById(selectedNewsId);
		newsToUpdate.setHidden(true);

		if(newsEJB.saveNews(newsToUpdate) != null){
			return "news-list.xhtml";
		}
		return ""; //TODO: do error handler
	}
	
	public void setFieldFromSelectedNews(){
		News news = newsEJB.getNewsById(selectedNewsId);
		setAuthor(news.getAuthor());
		setText(news.getText());
		setTitle(news.getTitle());
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
	
	public Integer getCommentLimit() {
		return null;
	}
	
	private News getNewsEntityFromFields() {
		News news = new News();
		news.setAuthor(this.author);
		news.setTitle(this.title);
		news.setText(this.text);
		news.setHidden(false);
		news.setCreated(Timestamp.from(Instant.now()));
		return news;
	}
	
}

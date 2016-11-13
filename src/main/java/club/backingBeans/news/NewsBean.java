package club.backingBeans.news;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.news.News;
import club.DAO.post.Post;
import club.EJB.interfaces.LocalNews;
import club.backingBeans.post.PostBean;
import club.backingBeans.user.LoginUserBean;


public abstract class NewsBean extends PostBean<News> {

	private int selectedNewsId;
	private News selectedNews;
	
	
	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;
	
	@EJB
	private LocalNews newsEJB;
	
	public NewsBean() {
	}
	
	@PostConstruct
	public void init() {
		super.setAuthor(loginUserBean.getUser());
	}
	
	
	public String create(){
		Post savedNews = super.save();
		
		if(savedNews != null) {
			return "post-details.xhtml?faces-redirect=true&id=" + savedNews.getId();
		} else {
			return "";
		}
	}
		
	public String deleteNews(){

		News newsToUpdate = newsEJB.getById(selectedNewsId);
		newsToUpdate.setHidden(true);

		if(newsEJB.save(newsToUpdate) != null){
			return "news-list.xhtml";
		}
		return "";
	}
		

	public void useSelectedNews(){
		selectedNews = newsEJB.getById(selectedNewsId);
	}

	
	public int getSelectedNewsId() {
		return selectedNewsId;
	}

	public void setSelectedNewsId(int selectedNewsId) {
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
	
	public Integer getCommentLimit() {
		return null;
	}
}

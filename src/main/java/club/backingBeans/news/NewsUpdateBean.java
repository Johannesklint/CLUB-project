package club.backingBeans.news;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.news.News;
import club.backingBeans.post.PostGetterBean;

@Named(value="newsUpdateBean")
@RequestScoped
public class NewsUpdateBean extends NewsBean {
	
	@Inject @Named(value="postGetterBean")
	private PostGetterBean postGetterBean;
	
	private int selectedId;
	
	@PostConstruct
	public void init() {
		News news = postGetterBean.getAsNews();
		setBeanFieldsFromEntity(news);
	}
	
	public String submit() {
		return updateAndGetRedirect();
	}
		
	private void setBeanFieldsFromEntity(News news) {
		setId(news.getId());
		setTitle(news.getTitle());
		setText(news.getText());
	}
	
	@Override
	public News updateFromFields() {
		
		News newsToUpdate = (News)super.getById(getId());
		newsToUpdate.setTitle(super.getTitle());
		newsToUpdate.setText(super.getText());
		
		return newsToUpdate;
	}

	public int getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(int selectedId) {
		throw new RuntimeException("");
	}

	@Override
	public News getFromFields() {
		// N/A
		return null;
	}
	
}

package club.backingBeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.Event;
import club.DAO.News;
import club.DAO.Post;
import club.EJB.interfaces.LocalPost;
import club.backingBeans.user.LoginUserBean;

@Named(value="postGetterBean")
@RequestScoped
public class PostGetterBean extends BasicFrontendBean {

	@Inject @Named(value="loginUserBean")
	LoginUserBean loginUserBean;

	@EJB
	private LocalPost postEJB;
	private Post selectedPost;
	
	@PostConstruct
	public void init() {
		
		super.redirectIfNotLoggedIn(loginUserBean);
		
		System.out.println("INIT");
		
		Map<String, String> params =  FacesContext.getCurrentInstance()
				.getExternalContext()
				.getRequestParameterMap();
		String selectedIdString = params.get("id");
		
		
		
		if(selectedIdString != null) {
			
			selectedPost = postEJB.getById(Integer.valueOf(selectedIdString));
			System.out.println("selected post"+selectedPost);
		}
	}
	
	public List<Post> getAll() {
		return postEJB.getAll();
	}
	
	public String deletePost() {
		return "";
	}
	
	public Post getSelectedPost() {
		return selectedPost;
	}
	
	public boolean isNews() {
		return selectedPost instanceof News;
	}
	
	public boolean isEvent() {
		return selectedPost instanceof Event;
	}
	
	public News getAsNews() {
		return (News) selectedPost;
	}
	
	public Event getAsEvent() {
		return (Event) selectedPost;
	}
	
	public Post getAsPost() {
		return selectedPost;
	}

}

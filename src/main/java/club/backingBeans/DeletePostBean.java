package club.backingBeans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.News;
import club.DAO.Post;

@RequestScoped
@Named(value="deletePostBean")
public class DeletePostBean extends PostBean<Post> {

	@Inject @Named(value="postGetterBean")
	private PostGetterBean postGetterBean;

	public String submit() {
		int id = postGetterBean.getSelectedPost().getId();
		setId(id);
		return super.deleteAndRedirect();
	}
	
	@Override
	public News getFromFields() {
		return null;
	}

	@Override
	public News updateFromFields() {
		return null;
	}

}

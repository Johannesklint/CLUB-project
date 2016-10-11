package club.backingBeans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import club.DAO.Comment;
import club.DAO.Post;
import club.EJB.interfaces.LocalPost;

@Named(value="testCommentBean")
@RequestScoped
public class TestComment {

	@EJB
	private LocalPost postEJB;

	
	public List<FormBean> getAll() {
		
		List<FormBean> r = new ArrayList<FormBean>();
		for(Comment c : postEJB.getById(1).getComments()) {
			r.add(new FormBean(c));
		}
		return r;
	}
	
}

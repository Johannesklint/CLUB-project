package club.backingBeans.comment;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import club.DAO.Comment;
import club.backingBeans.ListNewsBean;

@Named(value="commentListLimitBean")
@RequestScoped
public class CommentListLimitBean {

	@Inject @Named(value="listNewsBean")
	private ListNewsBean listNews;
			
	public boolean render(Comment c, int limit) {

		//Integer limit = getLimit();
		//if(limit==null) return true;
		
		int size = c.getPost().getComments().size();
		int index = 0;
		for(Comment compareComment : c.getPost().getComments()) {
			if(index>=size-limit) {
				if(compareComment==c) return true;
			}
			index++;
		}
		return false;
	}
	
	private Integer getLimit() {
		
		System.out.println("---");
		System.out.println(listNews);
		
		if(listNews==null) {
			return null;
		}
		return 3; // TODO: fix?
	}

}

package club.backingBeans.comment;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import club.DAO.comment.Comment;

@Named(value="commentListLimitBean")
@Dependent
public class CommentListLimitBean {
			
	public boolean render(Comment c, int limit) {
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
}

package club.backingBeans.comment;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import club.DAO.Comment;
import club.EJB.interfaces.LocalComment;

@Named(value="deleteCommentBean")
@RequestScoped
public class DeleteCommentBean extends CommentBean{

	@EJB
	private LocalComment commentEJB;
	
	public String submit(){
		return delete();
	}
	
	public String delete(){
			System.out.println("DELETE FFS");
		
		Comment commentToDelete = commentEJB.getById(super.getSelectedCommentId());
		
			System.out.println("AFTER INIT COMMENTTODELTE - GET BY ID: " + super.getSelectedCommentId());
		
		commentToDelete.setHidden(true);
		
			System.out.println("AFTER SET HIDEN TO TRUE");
		
		boolean deletedComment = commentEJB.saveComment(commentToDelete);
		
			System.out.println("AFTER BOOLEAN DELETED-COMMENT");
		
		if(deletedComment){
				System.out.println("AFTER IF TURNED TRUE");
			
			return "post-details.xhtml?faces-redirect=true&id=" + commentToDelete.getPost().getId();
		}else{
				System.out.println("AFTER IF TURNED FALSE");
			
			super.addFacesMessage("Could not delete");
		}return "post-details.xhtml?faces-redirect=true&id=" + commentToDelete.getPost().getId();
	}
}

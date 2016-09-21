package club.backingBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "testAjax")
@SessionScoped
public class TestAjaxBean implements Serializable {

	private static final long serialVersionUID = -3266235161238072427L;
	
	private List<String> comments;
	private String commentToRemove;
	private String newComment;
	




	public TestAjaxBean() {
		System.out.println("Running constructor of TestAjaxBean");
		comments = new ArrayList<>();
		
		comments.addAll(Arrays.asList("Hej hej nr 1,Hejhej nr 2,Hejhej nr 3,Hej nr 4,Hej nr 5".split(",")));
		
	}
	
	public String getNewComment() {
		return newComment;
	}
	
	public void setNewComment(String newComment) {
		System.out.println("Setting new comment: " + newComment);
		this.newComment = newComment;
	}
	

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	public String addNewComment() {
		System.out.println("Adding comment: " + newComment);
		comments.add(newComment);
		
		// reset
		newComment = "";
		return "";
	}

	public String removeSelectedComment() {
		System.out.println("Removing comment..");
		comments.remove(commentToRemove);
		
		return "";
	}
	
	public String getCommentToRemove() {
		return commentToRemove;
	}

	public void setCommentToRemove(String commentToRemove) {
		this.commentToRemove = commentToRemove;
	}
	
	
}

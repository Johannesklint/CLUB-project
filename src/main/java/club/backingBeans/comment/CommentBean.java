package club.backingBeans.comment;

import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import club.DAO.Comment;
import club.DAO.Post;
import club.DAO.User;
import club.EJB.interfaces.LocalComment;
import club.backingBeans.BasicFrontendBean;
import club.backingBeans.user.LoginUserBean;

public abstract class CommentBean extends BasicFrontendBean{

	private String text;
	private User author;
	private Post post;
	private LocalDateTime created;
	private int selectedCommentId;
	
	@EJB
	private LocalComment commentEJB;
	
	@Inject @Named("loginUserBean")
	private LoginUserBean loginUserBean;
	
	@PostConstruct
	public void init() {
		//redirectIfNotLoggedIn(); //TODO: fix redirect
		this.author = loginUserBean.getUser();
	}

	public CommentBean(){
	}
	
	public void setFieldFromSelectedComment(){
		Comment comment = commentEJB.getById(selectedCommentId);
		setText(comment.getText());
	}
	
	public String getText() {
		System.out.println("getText: " + text);
		return text;
	}
	
	public void setText(String text) {
		System.out.println("setText: " + text);
		this.text = text;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public int getSelectedCommentId() {
		System.out.println("getSelectedCommentId: " + selectedCommentId);
		return selectedCommentId;
	}

	public void setSelectedCommentId(int selectedCommentId) {
		this.selectedCommentId = selectedCommentId;
	}

	public LoginUserBean getLoginUserBean() {
		return loginUserBean;
	}

	public void setLoginUserBean(LoginUserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	}
	
	


}

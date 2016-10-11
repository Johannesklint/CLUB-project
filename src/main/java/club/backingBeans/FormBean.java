package club.backingBeans;

import javax.naming.Context;
import javax.naming.InitialContext;
import club.DAO.Comment;
import club.EJB.interfaces.LocalComment;

public class FormBean {

	private Comment comment;
	LocalComment commentEJB;
	
	public FormBean(Comment comment) {
		
		Context ctx;
		try {
			ctx = new InitialContext();
			commentEJB = (LocalComment) ctx.lookup("java:comp/env/commentEJB");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("An error");
		}
		this.comment = comment;
	
	}
	
	public String submit() {
		return "";
	}
	
	public String getText() {
		return comment.getText();
	}

	public void setText(String text) {
		this.comment.setText(text);
		commentEJB.saveComment(this.comment);
	}
}

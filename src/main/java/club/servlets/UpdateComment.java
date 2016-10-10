package club.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import club.DAO.Comment;
import club.EJB.interfaces.LocalComment;

/**
 * Servlet implementation class UpdateComment2
 */
@WebServlet("/update-comment")
public class UpdateComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private LocalComment commentEJB;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String commentId = request.getParameter("comment_id");
		String redirect = request.getParameter("redirect");
		String text = request.getParameter("text");
		System.out.println("c:"+commentId);
		if(commentId==null || text==null) {
			response.sendError(400);
			return;
		}

		Integer commentIdI = Integer.parseInt(commentId);
		
		Comment commentToUpdate = commentEJB.getById(commentIdI);
		commentToUpdate.setText(text);
		//commentToUpdate.setCreated(Timestamp.from(Instant.now())); 
		
		commentEJB.saveComment(commentToUpdate);		

		if(redirect!=null) {
	    	response.sendRedirect(redirect);	    				
		}
		else response.getWriter().append("Success (but no redirect)");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

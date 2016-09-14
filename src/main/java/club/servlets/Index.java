package club.servlets;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import club.domain.LoginUserBean;

/**
 * Servlet implementation class Index
 */
@WebServlet("/")
public class Index extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private LoginUserBean bean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    final HttpSession session = request.getSession();

	    FacesContext.getCurrentInstance();

	   // LoginUserBean bean = (LoginUserBean) request.getSession().getAttribute("loginUser");

	    if(!bean.isValidLogin())
	    	response.sendRedirect("/clubproject/faces/login-index.xhtml");
	    else
	    	response.sendRedirect("/clubproject/faces/home-index.xhtml");	    	
	}

}

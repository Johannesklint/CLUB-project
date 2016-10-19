package club.authorizations;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import club.backingBeans.user.LoginUserBean;

@WebFilter(urlPatterns={
				"/faces/configure-platform.xhtml",
				"/faces/create-news.xhtml",
				"/faces/create-event.xhtml",
				"/faces/post-update.xhtml",
				"/faces/user-management.xhtml"
})
public class AdminAuthorization implements Filter {

	@Inject @Named(value="loginUserBean")
	LoginUserBean loginUserBean;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(!loginUserBean.isLoggedIn() || !loginUserBean.getUser().getAdmin()) {
			
			HttpServletResponse hsr = (HttpServletResponse) response;
		    hsr.setStatus(403);
			request.getRequestDispatcher("error403.xhtml").forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {		
	}

}

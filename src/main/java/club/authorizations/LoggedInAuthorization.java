package club.authorizations;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import club.backingBeans.user.LoginUserBean;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@WebFilter(urlPatterns={
		"/faces/home.xhtml",
		"/faces/post-details.xhtml",
		"/faces/news-list.xhtml",
		"/faces/events-list.xhtml",
		"/faces/user-profile.xhtml"
})
public class LoggedInAuthorization implements Filter {

	@Inject @Named(value="loginUserBean")
	LoginUserBean loginUserBean;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(!loginUserBean.isLoggedIn()) {

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

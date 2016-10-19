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
		"/",
		"/faces/index.xhtml"
})
public class RedirectFromIndex implements Filter {

	@Inject @Named(value="loginUserBean")
	LoginUserBean loginUserBean;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(loginUserBean.isLoggedIn()) {

			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("/clubproject/faces/home.xhtml");
			return;
		}
		
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
	}

}

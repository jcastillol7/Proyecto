package umg.edu.jersey.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("corsFilter")
public class SpringCrossFilter extends BaseCORSFilter implements Filter {
	
	@Value("${cors.allowed.origins}")
	String allowedOriginsString;

	public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
		if (req instanceof HttpServletRequest) 
		{
			if (((HttpServletRequest) req).getHeader("Origin") != null) 
			{
				String origin = ((HttpServletRequest) req).getHeader("Origin");
				if (getAllowedOrigins(allowedOriginsString).contains(origin)) 
				{
					HttpServletResponse response = (HttpServletResponse) res;
					response.setHeader("Access-Control-Allow-Origin", "*");
					response.setHeader("Access-Control-Allow-Methods","GET, POST, DELETE, OPTIONS, PUT");
					response.setHeader("Access-Control-Allow-Headers","X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version,X-HTTP-Method-Override, Origin, Authorization");
					response.setHeader("Access-Control-Allow-Credentials", "true");
					response.setHeader("Access-Control-Expose-Headers", "Location");
					response.setHeader("Access-Control-Max-Age","3600");
				}
			}
		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}

package kr.pe.project.aop;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//@Component
//@Aspect
public class LoginIntercepter implements HandlerInterceptor {

	public List loginEssential = Arrays.asList("/**");

//	public List loginInessential = Arrays.asList("/post/board/**", "/post/read/**", "/post/like/**");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String loginId = (String) request.getSession().getAttribute("user");

		if (loginId != null) {
			return false;
		}

		else {
			String destUri = request.getRequestURI();
			System.out.println("왜 안되냐!!!");
//			String destQuery = request.getQueryString();
//			String dest = (destQuery == null) ? destUri : destUri + "?" + destQuery;
//			request.getSession()
			request.getSession().invalidate();
			response.sendRedirect("/index.html");
			return true;
		}
	}

}

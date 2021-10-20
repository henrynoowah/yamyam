package kr.pe.project.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.pe.project.model.domain.dto.PetUserDTO;
import kr.pe.project.model.domain.dto.PetUserDTO.Session;

@Component
@Aspect
public class SecurityAspect {
	
	// Custom Annotation 을 만들고 특정 annotation 이 있는 메소드는 pointcunt에서 제외!
	
	@Before("execution(public * kr.pe.project.controller.*.*(..))"
			+ "&& !@annotation(kr.pe.project.annotation.NoSessionCheck))")
	private String sessionCheck() throws Exception {
		
		String result = "test";
		
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
		System.out.println("---------AOP---------");
		PetUserDTO.Session user = (Session) session.getAttribute("user");
		System.out.println(user);
		System.out.println("---------AOP---------");
		if(user == null) {
			session.invalidate();
			throw new Exception("session end");
		};
		
		return result;
	};
	
}

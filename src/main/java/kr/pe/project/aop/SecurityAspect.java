package kr.pe.project.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import kr.pe.project.model.domain.dto.PetUserDTO;
import kr.pe.project.model.domain.dto.PetUserDTO.Session;

@Component
@Aspect
public class SecurityAspect {

//	execution(* step02.aop.biz.Car.buy*(..))
	@After("execution( * kr.pe.project.controller.PetUserController.checkSession(..))")
	private String sessionCheck() throws Exception {
		
		String result = null;
		
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
		System.out.println("---------AOP---------");
		PetUserDTO.Session user = (Session) session.getAttribute("user");
		System.out.println(user);
		System.out.println("---------AOP---------");
		if(user == null) {
			result = "session ended";
		};
		
		return result;
	};
	
}

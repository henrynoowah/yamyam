package kr.pe.project.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.pe.project.model.domain.dto.PetUserDTO;
import kr.pe.project.model.domain.dto.PetUserDTO.Session;

@Component
@Aspect
public class SecurityAspect {

	@Autowired
	private HttpSession session;
		
	@Before("within(kr.pe.project.controller.FoodController)")
	private void sessionCheck() throws Exception {
		System.out.println("---------AOP---------");
		PetUserDTO.Session user = (Session) session.getAttribute("user");
		System.out.println(user);
		System.out.println("---------AOP---------");
		if(user == null) {
			throw new Exception("로그아웃되었습니다 다시 로그인해주세요");
		}
	};
	
}

package kr.pe.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.pe.project.dao.PetUserRepository;
import kr.pe.project.model.domain.PetUser;
import kr.pe.project.model.domain.dto.PetUserDTO;

@RestController
@SessionAttributes({"user"})
public class PetUserController {
	
	PetUserController(){
		System.out.println("PetUserController(){}");
	}
	
	@Autowired
	private PetUserRepository dao;
	
	
	@PostMapping("login")
	public String login(PetUserDTO.Login login, HttpSession session) throws Exception {
		PetUser user = null;
		user = dao.findPetUserById(login.getId());
		System.out.println(login.getId());
		
		if(user != null) {
			if(!(user.getPw().equals(login.getPw()))) {
				throw new Exception("비밀번호가 틀렸습니다");
			}
		} else {
			throw new Exception("아이디가 존재하지 않습니다");
		}
		
		PetUserDTO.Session loggedIn = new PetUserDTO.Session();;
		loggedIn.setId(user.getId());
		loggedIn.setAdmin(user.getAdmin());
		session.setAttribute("user", loggedIn);
		
		return "로그인 성공 시 redirect 할 링크 작성 (foodList.html)";
	}
	
	@GetMapping("logout")
	public String logout(SessionStatus status) throws Exception {
		status.setComplete();
		return "회원가입 성공 시 자동 로그인 후 redirect 할 링크 작성 (foodList.html)";
	}
	
	@PostMapping("register")
	public String register(PetUserDTO.Register register) throws Exception {
		PetUser user = null;
		user = dao.findPetUserById(register.getId());
		
		if(user != null) {
			throw new Exception("이미 존재하는 아이디입니다");
		} else {
			dao.save(register.toEntity());
		}
		return "회원가입 성공 시 자동 로그인 후 redirect 할 링크 작성 (foodList.html)";
	}
	
	@ExceptionHandler
	public void PetUserException(Exception e) {
		System.out.println(e.getMessage());
	}

}

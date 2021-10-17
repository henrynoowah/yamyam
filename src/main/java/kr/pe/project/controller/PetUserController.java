package kr.pe.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.project.annotation.NoSessionCheck;
import kr.pe.project.dao.PetUserRepository;
import kr.pe.project.model.domain.PetUser;
import kr.pe.project.model.domain.dto.PetUserDTO;
import kr.pe.project.model.domain.dto.PetUserDTO.Session;

@RestController
//@SessionAttributes({"user"})
public class PetUserController {
	
	PetUserController(){
		System.out.println("PetUserController(){}");
	}
	
	@Autowired
	private PetUserRepository dao;
	
	@PostMapping("checkSession")
	public void checkSession() throws Exception {}
	
	@GetMapping("checkAdmin")
	public Integer checkAdmin(HttpSession session) {
		PetUserDTO.Session user = (Session) session.getAttribute("user");
		return user.getAdmin();
	}
	
	@NoSessionCheck
	@PostMapping("login")
	public PetUser login(PetUserDTO.Login login, HttpSession session) throws Exception {
		
		PetUser user = null;
		user = dao.findPetUserById(login.getId());
		System.out.println(login.getId());
		
		if(user != null) {
			if(!(user.getPw().equals(login.getPw()))) {
				throw new Exception("비밀번호가 틀렸습니다");
			} else {
				PetUserDTO.Session loggedIn = new PetUserDTO.Session();;
				loggedIn.setId(user.getId());
				loggedIn.setAdmin(user.getAdmin());
				session.setAttribute("user", loggedIn);
			}
		} else {
			throw new Exception("아이디가 존재하지 않습니다");
		}
		return user;
	}
	
	@NoSessionCheck
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.invalidate();
		return "index.html";
	}
	
	@NoSessionCheck
	@PostMapping("register")
	public String register(PetUserDTO.Register register) throws Exception {
		PetUser user = null;
		user = dao.findPetUserById(register.getId());
		
		if(user != null) {
			throw new Exception("이미 존재하는 아이디입니다");
		} else {
			dao.save(register.toEntity());		
		}
		return "회원가입 성공!";
	}

	@PostMapping("editPetUser")
	public String editPetUser(PetUserDTO.Edit petUser) throws Exception {
		PetUser user = dao.findById(petUser.getId()).get();
		
		if (petUser.getAnimalType().equals("") 
				|| petUser.getName().equals("") 
				|| petUser.getWeight().equals("")) {
			throw new Exception("작성하지 않은 항목이 존재합니다.");
		}
		
		user.setAnimalType(petUser.getAnimalType());
		user.setName(petUser.getName());
		user.setWeight(petUser.getWeight());
		
		if(petUser.getPw().equals(user.getPw())) {
			throw new Exception("이전 비밀번호와 일치합니다");
		} else if (petUser.getPw() != "") {
			user.setPw(petUser.getPw());
		}
		dao.save(user);
		
		return "수정완료";
	}
	
	@DeleteMapping("deletePetUser")
	public String deletePetUser(PetUserDTO.Delete petUser, HttpSession session) {
		dao.deleteById(petUser.getId());
		session.invalidate();
		return "🐾🐾🐾🐾🐾🐾🐾🐾🐾🐾";
	}
}

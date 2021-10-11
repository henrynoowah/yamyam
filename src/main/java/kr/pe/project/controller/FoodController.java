package kr.pe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.project.dao.FoodCatRepository;
import kr.pe.project.dao.FoodDogRepository;
import kr.pe.project.dao.FoodRepository;
import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.FoodCat;

@RestController
public class FoodController {
	
	FoodController(){
		System.out.println("FoodController(){} ---");
	}
	
	@Autowired
	private FoodRepository dao;
	
	@Autowired
	private FoodCatRepository catDao;
	
	@Autowired
	private FoodDogRepository dogDao;
	
	// 일반회원 + 관리자용 메소드
	// 전체 조회
	@GetMapping("foodListAll")
	public Iterable<Food> foodListAll() {
		Iterable<Food> foodListAll = dao.findAll();
		System.out.println(foodListAll);
		
		return foodListAll;
	}
	
	// 직접 검색해서 찾는 조회
	// 1. Search Engine
	// 2. 카테고리별 조회 
	
	// 은진A 담당
	// 관리자만 사용할 메소드
	// 음식 추가/ 수정
	// 음식 마다 FoodInfo 상속받은 객체에 대한 정보 추가 /수정
	
	
	
//	@GetMapping("addFood")
//	public String register() {
//		Food food = new Food();
//		food.setName("유영훈");
//		food.setCategory("정은진");
//		food.setCat(null);
//		food.setDog(null);
//		dao.save(food);
//		return "test";
//		
//	}
//	
//	@GetMapping("foodCatTest")
//	public String foodCatTest() {
//		FoodCat cat = new FoodCat();
////		
////		cat.setId(1);
////		cat.setEatable(1);
////		cat.setAmount("사과 반쪽");
////		cat.setInfo("사과는 맛있다");
////		
////		catDao.save(cat);
//		cat = catDao.findById((long) 1).get();
//		
//		
//		return "" + cat.getInfo();
//	}
	
}

package kr.pe.project.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.project.dao.FoodCatRepository;
import kr.pe.project.dao.FoodDogRepository;
import kr.pe.project.dao.FoodRepository;
import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.FoodCat;
import kr.pe.project.model.domain.FoodDog;
import kr.pe.project.model.domain.dto.FoodCatDTO;
import kr.pe.project.model.domain.dto.FoodDTO;
import kr.pe.project.model.domain.dto.FoodDogDTO;

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
	
	//음식명으로 조회
	@GetMapping("searchFoodName")
	public Food searchFoodName(String foodName) {
		Food food = dao.findFoodByName(foodName); //'사과'로 검색한다면 사과뿐만 아니라, '사과'가 들어간 음식 모두 조회하기? ex 사과주
		
		if (food != null) {
			System.out.println(food);			
			return food;
		} else {
			System.out.println("null 출력");
			return null;
		}
	}
	
	//카테고리로 조회 
	@GetMapping("searchFoodCategory")
	public List<Food> searchFoodCategory(String category) {
		List<Food> foods = dao.findFoodByCategory(category);
		
		if (foods != null) {
			System.out.println(foods);
			return foods;
		} else {
			System.out.println("null 출력");
			return null;
		}
	}
	
	//동물로 조회
	@GetMapping("searchAnimal")
	public List searchAnimal(String animalName) throws Exception {
		if (animalName.equals("강아지")) {
			List<FoodDog> dogList = dogDao.findFoodDogByEatable(1);
			return dogList;
		} else if (animalName.equals("고양이")) {
			List<FoodCat> catList = catDao.findFoodCatByEatable(1);
			return catList;
		} else {
			throw new Exception("해당 동물에 관한 정보가 없습니다");
		}
	}
	
	/* administrator */
	
	//add food
	@GetMapping("addFood")
	public String addFood(FoodDTO.Add food) throws Exception { 
		Food findFood = dao.findFoodByName(food.getName());
		
		if (findFood != null) {
			throw new Exception("이미 존재하는 음식입니다.");
		} else {
			dao.save(food.toEntity());
		}
		
		return "추가 완료";
	}
	
	//add foodCat
	@GetMapping("addInfoCat")
	public String addInfoCat(String foodName, FoodCatDTO.Add info) {
		Food food = dao.findFoodByName(foodName);
		info.setId(food.getId());
		
		catDao.save(info.toEntity());
		
//		food.setCat(info.toEntity());
		dao.save(food);
		
		return "추가 완료";
	}
	
	//add foodDog
	@GetMapping("addInfoDog")
	public String addInfoDog(String name, FoodDogDTO.Add info) {
		Food food = dao.findFoodByName(name);
		info.setId(food.getId());
		
		dogDao.save(info.toEntity());
		
		return "추가 완료";
	}
	
	//edit foodCat
	@GetMapping("editFoodCat")
	//빈칸일 경우 프론트에서 경고창 뜨게 가능?
	public String editFoodCat(FoodCatDTO.Update info) throws Exception { 
		if (info.getAmount() != null
			&& info.getEatable() != null
			&& info.getInfo() != null) {
			System.out.println(catDao.save(info.toEntity()));
		} else {
			throw new Exception("작성되지 않은 항목이 있습니다.");
		}
		
		return "test";
	}
	
	//edit foodDog
	@GetMapping("editFoodDog")
	public String editFoodDog(FoodDogDTO.Update info) throws Exception { 
		if (info.getAmount() != null && 
			info.getEatable() != null && 
			info.getInfo() != null) {
			System.out.println(dogDao.save(info.toEntity()));
		} else {
			throw new Exception("작성되지 않은 항목이 있습니다.");
		}
		
		return "test";
	}
	
	//edit food
	@GetMapping("editFood")
	public String editFood(FoodDTO.Update info) throws Exception {
    
		Food food = dao.findById(info.getId()).get();
		food.setName(info.getName());
		food.setCategory(info.getCategory());
		
		//동물이 늘어나면 set... 코드가 한 줄씩 계속 늘어나겠군,,,, 비효율적인 것 같다
//		try {
//			food.setCat(catDao.findById(info.getId()).get());
//		} catch (NoSuchElementException e) {
//			System.out.println("cat 정보 없음");
//		}
//		
//		try {
//			food.setDog(dogDao.findById(info.getId()).get());
//		} catch (NoSuchElementException e) {
//			System.out.println("dog 정보 없음");
//		}
	
		dao.save(food);
		
		return "test";
	}
	
	// 직접 검색해서 찾는 조회
	// 1. Search Engine - 어떤 걸 검색할건지 (동물로? 음식 명으로? 음식 카테고리로?)
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

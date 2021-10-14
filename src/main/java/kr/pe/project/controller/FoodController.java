package kr.pe.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.project.dao.AnimalInfoRepository;
import kr.pe.project.dao.FoodInfoRepository;
import kr.pe.project.dao.FoodRepository;
import kr.pe.project.model.domain.AnimalInfo;
import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.FoodInfo;
import kr.pe.project.model.domain.dto.AnimalInfoDTO;
import kr.pe.project.model.domain.dto.FoodDTO;

@RestController
public class FoodController {
	
	FoodController(){
		System.out.println("FoodController(){} ---");
	}
	
	@Autowired
	private FoodRepository foodDao;
	
	@Autowired
	private AnimalInfoRepository animalInfoDao;
	
	@Autowired
	private FoodInfoRepository foodInfoDao;
	
	// 일반회원 + 관리자용 메소드
	// 전체 조회
	@GetMapping("foodListAll")
	public Iterable<Food> foodListAll() {
		Iterable<Food> foodListAll = foodDao.findAll();
		System.out.println(foodListAll);
		
		return foodListAll;
	}
	
	//음식명으로 조회
	@GetMapping("searchFoodName")
	public Food searchFoodName(String foodName) {
		Food food = foodDao.findFoodByName(foodName); //'사과'로 검색한다면 사과뿐만 아니라, '사과'가 들어간 음식 모두 조회하기? ex 사과주
		
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
		List<Food> foods = foodDao.findFoodByCategory(category);
		
		if (foods != null) {
			System.out.println(foods);
			return foods;
		} else {
			System.out.println("null 출력");
			return null;
		}
	}
	
	@GetMapping("getAnimalInfo")
	public AnimalInfo getAnimalInfo(Long id) {
		AnimalInfo info = animalInfoDao.findById(id).get();
		return info;
	}
	
	
	@GetMapping("getFoodInfoAll")
	public Iterable<FoodInfo> getFoodInfoAll() {
		
		Iterable<FoodInfo> foodInfoAll = foodInfoDao.findAll();
		
		return foodInfoAll;
	}

	//동물로 조회
//	@GetMapping("searchAnimal")
//	public List searchAnimal(String animalName) throws Exception {
//		if (animalName.equals("강아지")) {
//			List<FoodDog> dogList = dogfoodDao.findFoodDogByEatable(1);
//			return dogList;
//		} else if (animalName.equals("고양이")) {
//			List<FoodCat> catList = catfoodDao.findFoodCatByEatable(1);
//			return catList;
//		} else {
//			throw new Exception("해당 동물에 관한 정보가 없습니다");
//		}
//	}
//	
//	/* administrator */
//	
	//add food
	@GetMapping("addFood")
	public String addFood(FoodDTO.Add food) throws Exception { 
		Food findFood = foodDao.findFoodByName(food.getName());
		
		if (findFood != null) {
			throw new Exception("이미 존재하는 음식입니다.");
		} else {
			try {
				foodDao.save(food.toEntity());
			} catch (Exception e) {
				throw new Exception("작성되지 않은 항목이 있습니다.");
			}
		}
		
		return "추가 완료";
	}
	
	//edit food
	@GetMapping("editFood")
	public String editFood(FoodDTO.Update food, long foodId) throws Exception {
		Food editFood = foodDao.findById(foodId).get();
		
		String name = food.getName();
		String category = food.getCategory();
		
		if (name != null && category != null) {
			editFood.setName(name);
			editFood.setCategory(category);
			} else {
				throw new Exception("작성되지 않은 항목이 있습니다.");
			}
		
		editFood.setCategory(food.getCategory());
		editFood.setName(food.getName());
		
		foodDao.save(editFood);
		
		return "수정 완료";
	}
	

	//delete food
	@GetMapping("deleteFood")
	public String deleteFodd(long foodId) {
		foodDao.deleteById(foodId);
		
		return "삭제 완료";
	}
	
	//add food
	@GetMapping("addAnimalInfo")
	public AnimalInfo addAnimalInfo(AnimalInfoDTO.Add info, long foodId) throws Exception {
		AnimalInfo animalInfo = new AnimalInfo();
		try {
			animalInfo = animalInfoDao.save(info.toEntity());

			FoodInfo foodInfo = new FoodInfo();
			foodInfo.setId(animalInfo.getId());
			foodInfo.setAnimalInfo(animalInfo);
			foodInfo.setFood(foodDao.findById(foodId).get());
			
			foodInfoDao.save(foodInfo);
		} catch (Exception e) {
			throw new Exception("작성하지 않은 항목이 있습니다.");
		}
		
		return info.toEntity();
	}	
}

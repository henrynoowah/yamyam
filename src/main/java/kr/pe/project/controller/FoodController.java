package kr.pe.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kr.pe.project.dao.AnimalInfoRepository;
import kr.pe.project.dao.FoodInfoRepository;
import kr.pe.project.dao.FoodRepository;
import kr.pe.project.dao.PostRepository;
import kr.pe.project.model.domain.AnimalInfo;
import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.FoodInfo;
import kr.pe.project.model.domain.Post;
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
	
	@Autowired
	private PostRepository postDao;
	
	// 일반회원 + 관리자용 메소드
	// 전체 조회
	@GetMapping("foodListAll")
	public Iterable<Food> foodListAll() throws Exception {
		Iterable<Food> foodListAll = foodDao.findAll();
		System.out.println(foodListAll);
		
		return foodListAll;
	}
	
	//음식명으로 조회
	@GetMapping("searchFoodName")
	public Food searchFoodName(String foodName) {
		Food food = foodDao.findFoodByName(foodName); 
		
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
	
	//add food
	@PostMapping("addFood")
	public String addFood(FoodDTO.Add food) throws Exception { 
		Food findFood = foodDao.findFoodByName(food.getName());
		System.out.println(food);
		
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
		
		if (name.equals("") || category.equals("")) {
			throw new Exception("작성되지 않은 항목이 있습니다.");
			} else {
				editFood.setName(name);
				editFood.setCategory(category);
			}
		
		editFood.setCategory(food.getCategory());
		editFood.setName(food.getName());
		
		foodDao.save(editFood);
		
		return "수정 완료";
	}

	//delete food
	@DeleteMapping("deleteFood")
	public String deleteFood(long foodId) throws Exception {
		Food food = foodDao.findById(foodId).get();
//		kr.pe.project.model.domain.Food@226655b5
		System.out.println(food);
		Iterable<Post> postAll = postDao.findPostByFood(food);
		
		Iterable<FoodInfo> foodInfo = foodInfoDao.findFoodInfoByFood(food);
		
		foodInfo.forEach(v -> {
			try {
				deleteAnimalInfo(v.getAnimalInfo().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}	
		});

		postDao.deleteAll(postAll);
		foodInfoDao.deleteAll(foodInfo);
			
		foodDao.delete(food);
		
		return "삭제 완료";
	}

	
//	public void addAnimalInfoPage(HttpServletResponse response, Long foodId, Long animalId) throws IOException {
//		response.sendRedirect("addAnimalInfo.html");
//	}
	
//	@RequestMapping(value = "addAnimalInfoPage", method = RequestMethod.POST)
//	public String addAnimalInfoPage(@PathParam("foodId") Long foodId, 
//									@PathParam("animalId") Long animalId) {
//
//		
//		return "redirect:/addAnimalInfo.html";
//	}
	
//	@RequestMapping("addAnimalInfoPage")
//	public ModelAndView addAnimalInfoPage(Model model, Long foodId, Long animalId) {
//		
//		ModelAndView mv = new ModelAndView("addAnimalInfo.html");
////		mv.setViewName("addAnimalInfo.html");
//		mv.addObject("foodId", foodId);
//		mv.addObject("animalId", animalId);
//		
//		return mv;
//	}
	
	//add animalInfo - 미테스트 개그튼그
	@PutMapping("addAnimalInfo")
	public String addAnimalInfo(AnimalInfoDTO.Add info, long foodId) throws Exception {
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
		
		return "동물 음식정보 추가 성공!";
	}	

	@GetMapping("getAnimalInfo")
	public AnimalInfo getAnimalInfo(Long id) throws Exception {
		AnimalInfo info = animalInfoDao.findById(id).get();
		return info;
	}
	
	
	@GetMapping("getFoodInfoAll")
	public Iterable<FoodInfo> getFoodInfoAll() throws Exception {
		
		Iterable<FoodInfo> foodInfoAll = foodInfoDao.findAll();
		
		return foodInfoAll;
	}
	
	@PutMapping("editAnimalInfo")
	public String editAnimalInfo(long animalInfoId, AnimalInfoDTO.Update info) {
		AnimalInfo animalinfo = animalInfoDao.findById(animalInfoId).get();
		
		animalinfo.setAmount(info.getAmount());
		animalinfo.setEatable(info.getEatable());
		animalinfo.setInfo(info.getInfo());
		
		animalInfoDao.save(animalinfo);
		
		return "수정성공";
	}
	
	@DeleteMapping("deleteAnimalInfo")
	public String deleteAnimalInfo(long id) throws Exception {
		AnimalInfo animalInfo = animalInfoDao.findById(id).get();
		FoodInfo foodInfo = foodInfoDao.findFoodInfoByAnimalInfo(animalInfo);
		foodInfoDao.delete(foodInfo);				

		animalInfoDao.deleteById(animalInfo.getId());
		
		return "삭제성공";
	}
}

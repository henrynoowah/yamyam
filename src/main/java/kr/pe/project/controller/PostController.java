package kr.pe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.project.dao.FoodRepository;
import kr.pe.project.dao.PetUserRepository;
import kr.pe.project.dao.PostRepository;
import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.PetUser;
import kr.pe.project.model.domain.Post;
import kr.pe.project.model.domain.dto.PostDTO;

@RestController
public class PostController {
	
	PostController() {
		System.out.println("PostController(){} ------------");
	}
	
	@Autowired
	private PostRepository postDao;
	
	@Autowired
	private PetUserRepository userDao;
	
	@Autowired
	private FoodRepository foodDao;
	
	//addPost
	@PutMapping("addPost")
	public String addPost(PostDTO.Write post, String userId, long foodId) throws Exception {
		PetUser user = userDao.findById(userId).get();
		post.setUser(user);
		
		Food food = foodDao.findById(foodId).get();
		post.setFood(food);
		
		try {
			postDao.save(post.toEntity());
		} catch (Exception e) {
			throw new Exception("작성되지 않은 항목이 있습니다.");
		}
		
		return "리뷰 작성 완료!";
	}
	
	//editPost
	@PutMapping("editPost")
	public String editPost(PostDTO.Edit post, long postId) throws Exception {
		Integer star = post.getStar();
		String info = post.getInfo();
		String taste = post.getTaste();
		Post editPost = postDao.findById(postId).get();
		
		if (star == 0 || info.equals("") || taste.equals("")) {
			throw new Exception("작성되지 않은 항목이 있습니다.");
		} else {
			editPost.setStar(post.getStar());
			editPost.setTaste(post.getInfo());
			editPost.setInfo(post.getTaste());
			postDao.save(editPost);
		}
	
		return "수정완료";
	}
	
	//deletePost
	@DeleteMapping("deletePost")
	public String deletePost(long postId) {
		postDao.deleteById(postId);
		
		return "삭제완료";
	}
	
}

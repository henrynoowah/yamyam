package kr.pe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("addPost")
	public Post addPost(PostDTO.Write post, String userId, long foodId) throws Exception {
		PetUser user = userDao.findById(userId).get();
		post.setUser(user);
		
		Food food = foodDao.findById(foodId).get();
		post.setFood(food);
		
		Post pst = new Post();
		try {
			pst = postDao.save(post.toEntity());
		} catch (Exception e) {
			throw new Exception("작성되지 않은 항목이 있습니다.");
		}
		
		return pst;
	}
	
	//editPost
	@GetMapping("editPost")
	public Post editPost(PostDTO.Edit post, long postId) throws Exception {
		Integer star = post.getStar();
		String info = post.getInfo();
		String taste = post.getTaste();
		Post editPost = postDao.findById(postId).get();
		if (star != null && info != null && taste != null) {
			editPost.setStar(post.getStar());
			editPost.setInfo(post.getTaste());
			editPost.setTaste(post.getInfo());
			postDao.save(editPost);
			} else {
				throw new Exception("작성되지 않은 항목이 있습니다.");
			}
		
		return editPost;
	}
	
	//deletePost
	@GetMapping("deletePost")
	public String deletePost(long postId) {
		postDao.deleteById(postId);
		
		return "삭제완료";
	}
	
	@ExceptionHandler
	public String PetUserException(Exception e) {
		return e.getMessage();
	}
}

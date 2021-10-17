package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	//select * from book where title like '%?%'
//	List<Book> findBookByTitleContaining(String title);
	Iterable<Post> findPostByFood(Food food);
	
}

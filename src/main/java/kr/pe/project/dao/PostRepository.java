package kr.pe.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

	List<Post> findPostByFoodId(long foodId);
}

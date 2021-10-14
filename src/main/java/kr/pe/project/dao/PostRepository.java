package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}

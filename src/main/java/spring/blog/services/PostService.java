package spring.blog.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import spring.blog.models.Post;

/**
 * Services – hold the business logic. Often just call some repository method. 
 * Example: create new post / show a post for deleting / delete post. 
 * Services may have several implementations: DB based or stub based. 
 *
 */
public interface PostService {
	List<Post> findAll();
	Page<Post> findAll(Pageable pageable);
	List<Post> findLatest5();
	Post findById(Long id);
	Post create(Post post);
	Post edit(Post post);
	void deleteById(Long id);
}

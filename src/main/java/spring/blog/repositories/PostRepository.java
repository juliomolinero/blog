package spring.blog.repositories;

import java.util.List;

import spring.blog.models.Post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
// Needed for query parameters
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// Needed for deletions
/*
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
*/


/**
 * Create the interface UserRepository. 
 * 	Note that you will not provide any implementation for it. 
 * 	Spring Data JPA will implement it for you. 
 * 	This is part of the "magic" behind the "Spring Data" framework.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	/**
	 * Note that the JPQL query will be automatically implemented and mapped to the method findLatest5Posts() 
	 * 	in the service implementation provided by Spring Data. 
	 * Original. @Query("SELECT p FROM posts p LEFT JOIN FETCH p.author ORDER BY p.date DESC")
	 * 
	 * TODO Important !!! use native query set to true or it won't work
	 * 		@Query(value="SELECT p.* FROM posts p Where p.id=:pId ORDER BY p.date DESC", nativeQuery = true)
	 * 
	 * 	IIF you want to use parameters try this definition
	 * 		@Query(value="SELECT p.* FROM posts p Where p.id=:pId ORDER BY p.date DESC", nativeQuery = true)
	 * 		List<Post> findLates5Posts(Pageable pageable, @Param("pId") Long id);
	 * 
	 * @param pageable
	 * @return
	 */	
	@Query(value="SELECT p.* FROM posts p ORDER BY p.date DESC", nativeQuery = true)	
	List<Post> findLates5Posts(Pageable pageable);	
	
	/*
	@Modifying
	@Transactional
	@Query("DELETE FROM Posts p WHERE p.id = ?1")
	void deleteById(Long id);
	*/
}
package spring.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.blog.models.User;

/**
 * Create the interface UserRepository. 
 * 	Note that you will not provide any implementation for it. 
 * 	Spring Data JPA will implement it for you. 
 * 	This is part of the "magic" behind the "Spring Data" framework.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);

}

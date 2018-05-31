package spring.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import spring.blog.models.User;
import spring.blog.repositories.UserRepository;

/**
 * 
 * Taken from 
 * 
 * http://javasampleapproach.com/testing/datajpatest-with-spring-boot 
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void should_find_no_users_if_repository_is_empty() throws Exception {
		List<User> users = this.userRepository.findAll();		
		assertThat(users).isEmpty();
	}	
	
	@Test
	public void should_store_a_user() throws Exception {
		// Create a dummy user
		User goofy = new User( 1L, "goofy", "Goofy Molinero");
		// We need to set a password or it will throw out an exception
		goofy.setPasswordHash("password1");
		// Save changes
		goofy = this.userRepository.save( goofy);		
		// Make assertions		
		assertThat(goofy.getUserName()).isEqualTo("goofy");
		assertThat(goofy.getFullName()).isEqualTo("Goofy Molinero");		
		assertThat(goofy.getPasswordHash()).isEqualTo("password1");
		assertThat(goofy).hasFieldOrPropertyWithValue("userName", "goofy");
	}
	
	@Test
	public void should_delete_all_users() throws Exception {
		// Create dummy users
		User goofy = new User( 10L, "goofy", "Goofy Molinero");		
		User goofy2 = new User( 20L, "goofy2", "Goofy Molinero2");
		// We need to set a password or it will throw out an exception
		goofy.setPasswordHash("password1");
		goofy2.setPasswordHash("password2");
/*		
		// ==============================================================================
		// TODO!, no need to use these as we're trying TestEntityManager, just leaving it.
		// ==============================================================================
		// To save them individually
		//this.userRepository.save( goofy );
		//this.userRepository.save( goofy2 );		
		// ==============================================================================
		// To save them in one step
		List<User> users = new ArrayList<User>(){
			private static final long serialVersionUID = -3009157732242241606L;
		{ add(goofy); add(goofy2); }};
		this.userRepository.saveAll( users );
		// ==============================================================================
*/		
		
		// Need to explore more on how to use TestEntityManager
		// use merge instead of persist
		// https://stackoverflow.com/questions/24721688/org-hibernate-persistentobjectexception-detached-entity-passed-to-persist-whe
		this.entityManager.merge(goofy);
		this.entityManager.merge(goofy2);
		
		// Remove after inserting		
		this.userRepository.deleteAll();
		
		assertThat(this.userRepository.findAll()).isEmpty();		
	}

}

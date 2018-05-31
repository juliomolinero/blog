package spring.blog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import spring.blog.models.Post;
import spring.blog.models.User;
import spring.blog.repositories.PostRepository;

/**
 * 
 * Taken from 
 * 
 * http://javasampleapproach.com/testing/datajpatest-with-spring-boot 
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void should_be_empty() throws Exception{
		List<Post> posts = this.postRepository.findAll();
		assertThat(posts).isEmpty();		
	}
	
	@Test
	public void should_store_post_with_no_author() throws Exception{
		Post post = new Post( 1L, "This is the title", "This is the body", null );
		
		assertThat(post.getId()).isEqualTo(1L);
		assertThat(post.getAuthor()).isNull();
	}
	
	@Test
	public void should_store_post_with_author() throws Exception{
		User author = new User( 1L, "theAuthor", "Mister Rob");
		author.setPasswordHash("password1");
		Post post = new Post( 1L, "This is the title", "This is the body", author );
		
		assertThat(post.getId()).isEqualTo(1L);
		assertThat(post.getAuthor()).isNotNull();
		assertThat(post.getAuthor().getUserName()).isEqualTo("theAuthor");		
	}
	
	@Test
	public void should_store_3_posts() throws Exception{
		User author = new User( 1L, "theAuthor", "Mister Rob");
		author.setPasswordHash("password1");
		// Make sure to store in entity manager
		this.entityManager.merge(author);
		
		Post post1 = new Post( 1L, "This is the title", "This is the body", author );
		Post post2 = new Post( 2L, "This is the title 2", "This is the body 2", author );
		Post post3 = new Post( 3L, "This is the title 3", "This is the body 3", null );
		
		this.entityManager.merge(post1);
		this.entityManager.merge(post2);
		this.entityManager.merge(post3);
		
		List<Post> posts = this.postRepository.findAll();
		
		assertThat(posts).hasSize(3);		
		
	}
}

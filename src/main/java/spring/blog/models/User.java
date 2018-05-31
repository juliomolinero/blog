package spring.blog.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
// Needed for JPA (Java Persistence API)
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


/**
 * Annotate the Entity Classes: User and Post
 * Put JPA annotations (table and column mappings + relationship mappings) to the entity classes in order to make then ready 
 * 	for persistence in the database through the JPA / Hibernate technology
 *
 */
@Entity
@Table(name="users")
public class User {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30, unique = true)
	@NotEmpty(message = "Please provide your User Name")
	private String userName;
	
	@Column(length = 60)
	@Length(min = 5, message = "Your password must have at least 5 characters")
	@NotEmpty(message = "Please provide your password")
	private String passwordHash;
	
	@Column(length = 100)
	@NotEmpty(message = "Please provide your full name")
	private String fullName;
	
	@OneToMany(mappedBy = "author")
	private Set<Post> posts = new HashSet<>();
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}
	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the posts
	 */
	public Set<Post> getPosts() {
		return posts;
	}
	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	/**
	 * @param id
	 * @param userName
	 * @param fullName
	 */
	public User(Long id, String userName, String fullName) {
		this.id = id;
		this.userName = userName;
		this.fullName = fullName;
	}
	/**
	 * 
	 */
	public User() {
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", passwordHash=" + passwordHash + ", fullName=" + fullName + "]";
	}
	

}

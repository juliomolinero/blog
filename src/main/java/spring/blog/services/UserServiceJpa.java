package spring.blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import spring.blog.models.User;
import spring.blog.repositories.UserRepository;

@Service
@Primary
public class UserServiceJpa implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public boolean authenticate(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<User> findAll() {		
		return this.userRepository.findAll();
	}
	@Override
	public Page<User> findAll(Pageable pageable) {		
		return this.userRepository.findAll(pageable);
	}
	@Override
	public User findById(Long id) {
		return this.userRepository.getOne(id);
	}
	@Override
	public User create(User user) {
		// Encode user's password before adding it to database
		user.setPasswordHash(bCryptPasswordEncoder.encode(user.getPasswordHash()));
		return this.userRepository.save(user);
	}
	@Override
	public User edit(User user) {		
		return this.userRepository.save(user);
	}
	@Override
	public void deleteById(Long id) {
		this.userRepository.deleteById(id);		
	}
	@Override
	public User findByUserName(String userName) {
		return this.userRepository.findByUserName(userName);
	}	
}

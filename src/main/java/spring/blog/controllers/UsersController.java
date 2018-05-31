package spring.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.blog.models.User;
import spring.blog.services.UserService;

@Controller
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Get a list of all posts in the database, it should be able to paginate and sort
	 * http://localhost:8090/posts?page=0&size=3&sort=id
	 *  
	 * @param model
	 * @return
	 */
	@RequestMapping("/users")
	public String index(Model model, @PageableDefault(sort = {"userName"}, value = 5) Pageable pageable){		
		// Get the content of the table, TODO. find a way to paginate
		Page<User> users = this.userService.findAll(pageable);
		
		// Define variables to be passed to view
		model.addAttribute("users", users);
		// Return the view model itself
		return "users/index";
	}

}

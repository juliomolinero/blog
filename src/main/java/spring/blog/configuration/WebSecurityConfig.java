package spring.blog.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * Needed to read credentials parse password
	 * TODO https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d
	 */
	@Autowired
	private DataSource dataSource;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/**
	 * Read query statement from application.properties file
	 */
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
	
	/**
	 * Perform a query against the database with user name and password fields
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}
	/**
	 * Configure HTTP permissions
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
				.antMatchers("/", "/home", "/error/**", "/posts", "/posts/view/**", "/users/logout", "/users/register", "/users/login").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/users/login").failureUrl("/users/login?error=true").defaultSuccessUrl("/")
				.usernameParameter("userName").passwordParameter("passwordHash")
				.and()
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/error/403");
	}
	/**
	 * Configure Web permissions (images, css, js, etc.)
	 */
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring()
				.antMatchers("/resources/**", "/static/**", "/css/**", "/img/**", "/js/**");
		
	}

}

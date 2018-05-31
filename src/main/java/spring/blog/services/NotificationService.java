package spring.blog.services;

/**
 *  if the application says something like "Sorry, the post #150 is not found" in a good-looking form. 
 *  You might change the error page following the Spring Boot documentation: 
 *  	http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-error-handling-custom-error-pages. 
 *
 *	A good approach is to create a NotificationService, which will encapsulate the logic related 
 *	to adding and storing the info / error messages + add some code in the site layout template to show the messages (when available). 
 *	Let’s create a notification service, implementation of this service and notifications view template.
 */
public interface NotificationService {
	void addInfoMessage(String message);
	void addErrorMessage(String message);
}

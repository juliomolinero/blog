package spring.blog.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Custom Error Page
 * 
 * http://www.baeldung.com/spring-boot-custom-error-page
 * 
 * 
 *
 */
@Controller
public class CustomErrorController implements ErrorController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomErrorController.class);
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request){
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		String logError = "StatusCode->" + RequestDispatcher.ERROR_STATUS_CODE + "\n ErrorException->" + RequestDispatcher.ERROR_EXCEPTION.toString() + "\n ErrorException->" + RequestDispatcher.ERROR_MESSAGE.toString();
		log.error("<-------------------------------->");
		log.error("An error occured!!!");
		log.error(logError);
		log.error("<-------------------------------->");
		
		if( status != null ){
			Integer statusCode = Integer.valueOf(status.toString());
			if( statusCode.equals(HttpStatus.NOT_FOUND.value())) {
				return "error/404";
			} else if( statusCode.equals(HttpStatus.FORBIDDEN)) {				
				return "error/403";
			} else if( statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) {				
				return "error/500";
			}
		}
		// Do something like logging
		return "error/default";
	}
	
	@Override
	public String getErrorPath(){
		return "/error";
	}

}

/**
 * 
 */
package com.springcourse.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 001ZAV744
 *
 */

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET, path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@RequestMapping(method = RequestMethod.GET, path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World...");
	}
	
	/* Example Application for path variable
	 * URL: localhost:8080/hello-world-bean/path-variable/Bharath
	 */
	@RequestMapping(method = RequestMethod.GET, path="/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World, "+name);
	}
	
	/* Example Application for Internationalization
	 * URL: localhost:8080/hello-world-internationalized
	 */
	@RequestMapping(method = RequestMethod.GET, path="/hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null, 
				LocaleContextHolder.getLocale());
	}
	
}
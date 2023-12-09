package com.blogpress.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	
	
public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

//	@GetMapping("/hello")
	@RequestMapping(method = RequestMethod.GET, path="/hello")
	public String HelloWorld() {
		return "Hellow world";
	}
	
	@GetMapping("/hellobean")
	public hellobean HelloWorldBean() {
		return new hellobean("hello");
	}
	
	class hellobean{
		private String msg;

		public hellobean(String msg) {
			super();
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}
	}
	
	@GetMapping("/hellobean/{name}")
	public hellobean HelloWorldBean(@PathVariable String name) {
		return new hellobean("hello " + name);
	} 
	
	@GetMapping("/helloi18n")
	public String helloWorldI18n() {
		return messageSource.getMessage("good.morning.msg", null, "Default Msg", LocaleContextHolder.getLocale());
	}

}

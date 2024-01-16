package br.com.erudio;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	
	private static final AtomicLong cont = new AtomicLong();
	
	@GetMapping(value ="/greeting")
	Greeting greeting (@RequestParam(value = "name", defaultValue = "World") String name){
		return new Greeting(cont.incrementAndGet(),String.format(template, name));
	} //RequestParam faz o binding do parametro com o Queryparam 
}

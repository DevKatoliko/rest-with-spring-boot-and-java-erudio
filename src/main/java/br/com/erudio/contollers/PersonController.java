package br.com.erudio.contollers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.exceptions.UnsupportedMathOparationException;
import br.com.erudio.model.Person;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

	private static final AtomicLong cont = new AtomicLong();

	@Autowired
	PersonService personService;

	// --> @RequestMapping(value ="/sum/{number1}/{number2}", method =
	// RequestMethod.GET).
	@RequestMapping(value = "/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE) // No RequestMapping seria conveniente explicitar o tipo de requisição, no caso
								// GET.
	public Person findPersonByID(@PathVariable(value = "id") String id)  {
		return personService.findPersonById(id);
	} // PathVariable associa o valor do parâmetro com o valor da PathVariable(value =
		// number1) por exemplo.

	@RequestMapping(value = "/findAll",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll(){
		return personService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST,
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Person createPerson(@RequestBody Person person) {
		return personService.create(person);
	}
	
	@RequestMapping(method = RequestMethod.PUT , 
					consumes = MediaType.APPLICATION_JSON_VALUE,
					produces = MediaType.APPLICATION_JSON_VALUE)
	public Person updatePerson(@RequestBody Person person) {
		return personService.update(person);
	}
	
	@RequestMapping(value="/{id}", 
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void deletePerson(String id) {
		personService.delete(id);
	}

}
package br.com.erudio.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.model.Person;
import br.com.erudio.services.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

	

	@Autowired
	PersonService personService;

	
	@GetMapping(value = "/{id}", 
				produces = MediaType.APPLICATION_JSON_VALUE) // No RequestMapping seria conveniente explicitar o tipo de requisição, no caso
								// GET.
	public Person findPersonByID(@PathVariable(value = "id") Long id)  {
		return personService.findPersonById(id);
	} // PathVariable associa o valor do parâmetro com o valor da PathVariable(value =
		// number1) por exemplo.

	@GetMapping(value = "/findAll",
				produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> findAll(){
		return personService.findAll();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public Person createPerson(@RequestBody Person person) {
		return personService.create(person);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public Person updatePerson(@RequestBody Person person) {
		return personService.update(person);
	}
	
	@DeleteMapping(value="/{id}", 
			       produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deletePerson(@PathVariable Long id) {
		personService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
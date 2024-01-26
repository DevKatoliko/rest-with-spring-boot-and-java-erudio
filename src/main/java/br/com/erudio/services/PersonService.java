package br.com.erudio.services;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonRepository repository;
	
	private final AtomicLong cont = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll(){
		
		
		return repository.findAll();
	}
	


	public Person findPersonById (Long id) {
		logger.info("Finding one person");
		
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There was no person with this ID: " + id)); 
	}
	
	public Person create(Person person) {
		logger.info("Creating a person");
		
		return repository.save(person);
	}

	public Person update(Person person) {
		logger.info("Updating a person");
		var entity = findPersonById(person.getId());
		entity.setName(person.getName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repository.save(entity);
	}
	
	
	public void delete(Long id) {
		logger.info("Deleting a person");
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There was no person with this ID: " + id)); 
		repository.delete(entity);
	}
	

}

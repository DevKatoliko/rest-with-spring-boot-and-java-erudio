package br.com.erudio.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;

@Service
public class PersonService implements Serializable{
	private static final long serialVersionUID = 1L;

	private final AtomicLong cont = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<Person> findAll(){
		List<Person> persons = new ArrayList();
		for(int i = 1 ; i<=8 ; i++) {
			Person person = MockPerson(i);
			persons.add(person);
		}
		return persons;
	}
	


	public Person findPersonById (String id) {
		logger.info("Finding one person");
		Person person = new Person();
		person.setId(cont.incrementAndGet());
		person.setName("Rubens");
		person.setLastName("dos Santos de Maria");
		person.setAddress("Saint Joseph City - Parana - Brasil");
		person.setGender("Male");
		return person; // Isso é um Mock apenas!
	}
	
	public Person create(Person person) {
		logger.info("Creating a person");
		
		return person;
	}

	public Person update(Person person) {
		logger.info("Updating a person");
		
		return person;
	}
	
	
	public void delete(String id) {
		logger.info("Deleting a person");
		
		
	}
	private Person MockPerson(int i) {
		Person person = new Person();
		person.setId(cont.incrementAndGet());
		person.setName("Person " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Address" + i);
		person.setGender((i%2==0) ? "Male":"Female");
		return person; // Isso é um Mock apenas!
	}

}

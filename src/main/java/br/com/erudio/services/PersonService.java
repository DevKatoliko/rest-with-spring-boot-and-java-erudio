package br.com.erudio.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.contollers.PersonController;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	private PersonRepository repository;
	

	private PersonMapper mapper;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<PersonVO> findAll(){
		
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findPersonVOByID(p.getKey())).withSelfRel()));
	
		return persons;
	}
	


	public PersonVO findPersonVOById (Long id) {
		logger.info("Finding one person");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There was no person with this ID: " + id)); 
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findPersonVOByID(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating a person");
		if(person == null) throw new RequiredObjectIsNullException();
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findPersonVOByID(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating a person v2");
		var entity = mapper.convertVOToEntity(person);
		var vo = mapper.convertEntityToVO(repository.save(entity));// Primeiro o método persiste a entidade na base de dados e depois converte para VO
		return vo;
	}

	public PersonVO update(PersonVO person) {
		logger.info("Updating a person");
		if(person == null) throw new RequiredObjectIsNullException();
		var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("There was no person with this ID:"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		var vo = DozerMapper.parseObject(  repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findPersonVOByID(vo.getKey())).withSelfRel());
		return vo;
	}
	
	
	public void delete(Long id) {
		logger.info("Deleting a person");
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There was no person with this ID: " + id)); 
		repository.delete(entity);
	}
	

}

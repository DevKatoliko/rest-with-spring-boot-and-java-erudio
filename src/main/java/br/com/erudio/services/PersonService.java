package br.com.erudio.services;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.data.vo.v2.PersonVOV2;
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
	
	@Autowired
	private PersonMapper mapper;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public List<PersonVO> findAll(){
		
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	


	public PersonVO findPersonVOById (Long id) {
		logger.info("Finding one person");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There was no person with this ID: " + id)); 
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating a person");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		 return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating a person v2");
		var entity = mapper.convertVOToEntity(person);
		var vo = mapper.convertEntityToVO(repository.save(entity));// Primeiro o método converte a entidade para VO e depois persiste ela na base de dados
		return vo;
	}

	public PersonVO update(PersonVO person) {
		logger.info("Updating a person");
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("There was no person with this ID:"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	
	public void delete(Long id) {
		logger.info("Deleting a person");
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There was no person with this ID: " + id)); 
		repository.delete(entity);
	}
	

}

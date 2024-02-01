package br.com.erudio.mapper.custom;

import java.util.Date;

import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.model.Person;

public class PersonMapper {
	
	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setBirthDate(new Date());
		vo.setAddress(person.getAddress());
		vo.setGender(person.getGender());
		
		return vo;
	}
	
	public Person convertVOToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		//entity.setBirthDate(new Date());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return entity;
	}
}

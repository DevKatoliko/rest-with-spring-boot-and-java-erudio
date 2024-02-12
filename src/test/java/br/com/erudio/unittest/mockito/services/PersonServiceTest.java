package br.com.erudio.unittest.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;
import br.com.erudio.services.PersonService;
import br.com.erudio.unitittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS) // Define o ciclo de vida
@ExtendWith(MockitoExtension.class) // Extende da Classe
class PersonServiceTest {

	MockPerson input;

	@InjectMocks
	private PersonService service; // Serve para injetar os mocks para essa classe quando for invocado seus métodos
									// aqui

	@Mock
	PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();

	}

	@Test
	void testFindAll() {
		List <Person> list = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(list);
	
		var people = service.findAll();
		
		assertNotNull(people);
		assertEquals(14,people.size());
		
		var person1 = people.get(1);
		assertNotNull(person1);
		assertNotNull(person1.getKey());
		assertNotNull(person1.getLinks());
		assertTrue(person1.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		
		assertEquals("First Name Test1", person1.getFirstName());
		assertEquals("Last Name Test1", person1.getLastName());
		assertEquals("Addres Test1", person1.getAddress());
		assertEquals("Female", person1.getGender());

		var person4 = people.get(4);
		assertNotNull(person4);
		assertNotNull(person4.getKey());
		assertNotNull(person4.getLinks());
		assertTrue(person4.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
		
		assertEquals("First Name Test4", person4.getFirstName());
		assertEquals("Last Name Test4", person4.getLastName());
		assertEquals("Addres Test4", person4.getAddress());
		assertEquals("Male", person4.getGender());
	
		var person7 = people.get(7);
		assertNotNull(person7);
		assertNotNull(person7.getKey());
		assertNotNull(person7.getLinks());
		assertTrue(person7.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
		
		assertEquals("First Name Test7", person7.getFirstName());
		assertEquals("Last Name Test7", person7.getLastName());
		assertEquals("Addres Test7", person7.getAddress());
		assertEquals("Female", person7.getGender());
		
		
	
	}

	@Test
	void testFindPersonVOById() {
		Person entity = input.mockEntity(1);
		when(repository.findById(1L)).thenReturn(Optional.of(entity)); // Quando o service chamar esse método do
																		// repository, ele irá retornar a entidade
																		// mockada person
		var result = service.findPersonVOById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());// Extraído do HATEOAS que gera o link
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));// Extraído do HATEOAS que
																							// passa para string o link
																							// que ele gera
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Female", result.getGender());

	}

	@Test
	void testCreate() {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());// Extraído do HATEOAS que gera o link
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));// Extraído do HATEOAS que
																							// passa para string o link
																							// que ele gera
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Female", result.getGender());

	}

	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class,() ->{
			service.create(null);
		}); 
		String expected = "Could not persist object because it is null";
		String resived = exception.getMessage();
		assertTrue(resived.contains(expected));
																						
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class,() ->{
			service.update(null);
		}); 
		String expected = "Could not persist object because it is null";
		String resived = exception.getMessage();
		assertTrue(resived.contains(expected));
																						
	}
	
	@Test
	void testUpdate() {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var result = service.update(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());// Extraído do HATEOAS que gera o link
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));// Extraído do HATEOAS que
																							// passa para string o link
																							// que ele gera
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Female", result.getGender());

	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

}

package com.testing.batch.job.first;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
	final static Logger LOGGER = LoggerFactory.getLogger(PersonService.class);

	@Autowired
	private PersonRepository personRepository;

	@Transactional(readOnly = true)
	public List<Person> getAll() {
		return personRepository.findAll();

	}

	@Transactional
	public void save(Person person) {
		personRepository.save(person);
	}

	// 外部からこのメソッドを呼び出した場合、saveで例外が発生してもロールバックされない
	public void save2(Person entity) {
		save(entity);
	}

	@Transactional
	public Person saveAndFlush(Person person) {
		if (person != null) {
			person = personRepository.saveAndFlush(person);
		}

		return person;
	}

	@Transactional
	public void deleteAll() {
		personRepository.deleteAll();
	}
	
	@Transactional(readOnly = true)
  public List<Person> findAllByPersonIdDesc() {
    return personRepository.findAllByPersonIdDesc();

  }

//	public PersonService(PersonRepository personRepository) {
//		this.personRepository = personRepository;
//	}
//
//	public List<Person> findAll() {
//		return personRepository.findAll();
//	}
//
//	//@Modifying
//	public void deleteAll() {
//		personRepository.deleteAll();
//	}
//
    //@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
//	@Transactional
	public void createOne(Person person) {
		personRepository.save(person);
	}
//
//    //@Transactional(readOnly = false)
//	public void deleteById(Integer id) {
//		personRepository.deleteById(id);
//	}
//    
//	public void saveOne(Person person) {
//		personRepository.saveAndFlush(person);
//	}
	
	public void addOne(Person person) {
	  personRepository.insertPersonOne(person.getPersonId(), person.getFirstName(), person.getLastName());
	}

	public void delAll() {
	  personRepository.deleteAll();	  
	}
	
	public void saveAll(List<? extends Person> entities) {
    personRepository.saveAll(entities);
  }
}

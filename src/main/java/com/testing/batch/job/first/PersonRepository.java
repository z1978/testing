package com.testing.batch.job.first;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Transactional
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>  {
	List<Person> findById(int id);
}

package com.testing.batch.job.first;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//@Transactional
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
  List<Person> findById(int id);

  @Query(value = "INSERT INTO `person`(`person_id`, `first_name`, `last_name`) VALUES (?1, ?2, ?3)", nativeQuery = true)
  @Modifying
  int insertPersonOne(int id, String cellphone, String psd);
  
  
  @Query(value = "SELECT c.person_id FROM person c ORDER BY c.person_id DESC", nativeQuery = true)
  List<Person> findAllByPersonIdDesc();

}

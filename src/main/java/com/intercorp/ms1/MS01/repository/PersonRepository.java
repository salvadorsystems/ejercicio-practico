package com.intercorp.ms1.MS01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intercorp.ms1.MS01.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}

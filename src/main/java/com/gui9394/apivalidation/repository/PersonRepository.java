package com.gui9394.apivalidation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gui9394.apivalidation.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
package com.decagon.OakLandv1be.repositries;

import com.decagon.OakLandv1be.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

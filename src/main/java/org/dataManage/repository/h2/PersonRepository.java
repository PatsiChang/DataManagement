package org.dataManage.repository.h2;


import org.dataManage.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {
    Person save(Person person);

    List<Person> findAll();

    Optional<Person> findById(UUID uid);

    Optional<Person> findByUserId(String userId);

    void deleteById(UUID UID);
}

package org.dataManage.service;

import org.dataManage.entity.Person;
import org.dataManage.entity.PersonPG;
import org.dataManage.repository.h2.PersonRepository;
import org.dataManage.repository.postgres.SecondaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataMigrationService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SecondaryRepository secondaryRepository;

    public void migrateData() {
        Iterable<Person> h2Persons = personRepository.findAll();
        for (Person person : h2Persons) {
            PersonPG p = new PersonPG(person.getUid(), person.getUserId(), person.getName(),
                person.getEmail(), person.getPassword());
            secondaryRepository.save(p);
        }
    }


}

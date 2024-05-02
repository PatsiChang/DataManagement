package org.dataManage.repository.postgres;

import org.dataManage.entity.PersonPG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SecondaryRepository extends JpaRepository<PersonPG, UUID> {
    PersonPG save(PersonPG person);

    List<PersonPG> findAll();

    Optional<PersonPG> findById(UUID uid);

    Optional<PersonPG> findByUserId(String userId);

    void deleteById(UUID UID);
}

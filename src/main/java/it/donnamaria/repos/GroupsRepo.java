package it.donnamaria.repos;

import it.donnamaria.models.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepo extends JpaRepository<Groups, Long> {
    public boolean existsByName(String name);
    public Groups findByName(String name);
}

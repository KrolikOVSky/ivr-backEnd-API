package it.donnamaria.repos;

import it.donnamaria.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepo extends JpaRepository<Images, Long> {
}

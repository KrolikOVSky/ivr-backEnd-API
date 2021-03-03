package it.donnamaria.repos;

import it.donnamaria.models.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepo extends JpaRepository<Images, Long> {
}

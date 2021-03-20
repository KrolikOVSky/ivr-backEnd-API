package it.donnamaria.repos;

import it.donnamaria.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
    public boolean existsByName(String name);
}

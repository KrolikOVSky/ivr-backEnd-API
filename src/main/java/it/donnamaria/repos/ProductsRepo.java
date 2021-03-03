package it.donnamaria.repos;

import it.donnamaria.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products, Long> {
    public boolean existsByName(String name);
}

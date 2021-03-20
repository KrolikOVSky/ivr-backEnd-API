package it.donnamaria.repos.test;

import it.donnamaria.models.test.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
    public Cart findCartByName(String name);
}

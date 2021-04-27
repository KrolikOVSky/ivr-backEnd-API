//package it.donnamaria.models.test;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "items")
//public class Items{
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String name;
//
//    @ManyToOne
//    @JsonIgnore
//    private Cart cart;
//
//    public Items() {
//    }
//
//    public Items(String name, Cart cart) {
//        this.name = name;
//        this.cart = cart;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//    @Override
//    public String toString() {
//        return String.format("{ \"id\": \"%s\"; \"name\": \"%s\"; \"cart\": \"%s\"}", id, name, cart.toString());
//    }
//}

package it.donnamaria.controllers;

import it.donnamaria.models.test.Cart;
import it.donnamaria.models.test.Items;
import it.donnamaria.repos.test.CartRepo;
import it.donnamaria.repos.test.ItemsRepo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static it.donnamaria.Global.uploadPath;

@RestController
@RequestMapping("api/test")
public class TestController {
    private final CartRepo cartRepo;
    private final ItemsRepo itemsRepo;

    public TestController(CartRepo cartRepo, ItemsRepo itemsRepo) {
        this.cartRepo = cartRepo;
        this.itemsRepo = itemsRepo;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addFoo(@RequestParam(name = "file", required = false) MultipartFile file, @RequestParam("name") String name) {

        if (file != null) {
            System.out.println(file.getName());
            System.out.println("File Name ==> " + file.getOriginalFilename());
            System.out.println("name ==> " + name);


            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                System.out.println(uploadDir.mkdir());
            }

            String resultFile = UUID.randomUUID().toString() + "__" + name + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + resultFile));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else System.out.println("file is null");
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public void testJpaG(@RequestBody Map<String, String> data) {
        Cart cart = new Cart(data.get("name"));
        cartRepo.save(cart);
    }

    @RequestMapping("/cart/{id}")
    public Cart getCart(@PathVariable Long id) {
        var temp = cartRepo.findById(id);
        var rrr = temp.get();
        return rrr;
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public void testJpaP(@RequestBody Map<String, String> data) {
        Items items = new Items(data.get("name"), cartRepo.findCartByName(data.get("cartName")));
        itemsRepo.save(items);
    }

    @RequestMapping("/items")
    public List<Items> getItems() {
        var temp = itemsRepo.findAll();
        return temp;
    }

}

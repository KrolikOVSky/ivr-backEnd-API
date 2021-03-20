package it.donnamaria.controllers;

import it.donnamaria.models.Products;
import it.donnamaria.repos.GroupsRepo;
import it.donnamaria.repos.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static it.donnamaria.Global.*;

@RestController
@RequestMapping("api/product")
public class ProductController {

    private final ProductsRepo productsRepo;
    private final GroupsRepo groupsRepo;

    public ProductController(ProductsRepo productsRepo, GroupsRepo groupsRepo) {
        this.productsRepo = productsRepo;
        this.groupsRepo = groupsRepo;
    }

    @GetMapping
    public List<Products> getAllPro() {
        return productsRepo.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> addProd(@RequestParam(name = "price") Float price,
                                          @RequestParam(name = "name") String name,
                                          @RequestParam(name = "description") String description,
                                          @RequestParam(name = "shortDesc") String shortDesc,
                                          @RequestParam(name = "volume") String volume,
                                          @RequestParam(name = "file") MultipartFile file,
                                          @RequestParam(name = "groupId") String groupId) {
        if (productsRepo.existsByName(name)) return NotFoundResult;
        Products product = new Products(price, name, description, shortDesc, saveFile(file), volume, groupsRepo.getOne(Long.parseLong(groupId)));
        productsRepo.save(product);
        return OkResult;
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delProd(@PathVariable Long id) {
        if (productsRepo.existsById(id)) {
            productsRepo.delete(productsRepo.getOne(id));
            return OkResult;
        } else return NotFoundResult;
    }

    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> editProd(@RequestParam(name = "price") Float price,
                                           @RequestParam(name = "name") String name,
                                           @RequestParam(name = "description") String description,
                                           @RequestParam(name = "fullDesc") String fullDesc,
                                           @RequestParam(name = "volume") String volume,
                                           @RequestParam(name = "file", required = false) MultipartFile file,
                                           @RequestParam(name = "groupId") String groupId,
                                           @PathVariable Long id) {
        if (productsRepo.existsById(id)) {
            Products beforeProduct = productsRepo.getOne(id);
            if (file != null) beforeProduct.setImageFileName(saveFile(file));
            beforeProduct.setName(name);
            beforeProduct.setDescription(description);
            beforeProduct.setShortDesc(fullDesc);
            beforeProduct.setPrice(price);
            beforeProduct.setVolume(volume);
            beforeProduct.setRelatedGroup(groupsRepo.getOne(Long.parseLong(groupId)));
            productsRepo.save(beforeProduct);
            return OkResult;
        } else return NotFoundResult;
    }
}

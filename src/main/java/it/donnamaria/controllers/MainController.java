package it.donnamaria.controllers;

import it.donnamaria.models.Groups;
import it.donnamaria.models.Images;
import it.donnamaria.models.Products;
import it.donnamaria.repos.GroupsRepo;
import it.donnamaria.repos.ImagesRepo;
import it.donnamaria.repos.ProductsRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class MainController {
    private final ResponseEntity<String> NotFoundResult = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    private final ResponseEntity<String> OkResult = new ResponseEntity<>(HttpStatus.OK);
    private final ProductsRepo productsRepo;
    private final GroupsRepo groupsRepo;
    private final ImagesRepo imagesRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(ProductsRepo productsRepo, GroupsRepo groupsRepo, ImagesRepo imagesRepo) {
        this.productsRepo = productsRepo;
        this.groupsRepo = groupsRepo;
        this.imagesRepo = imagesRepo;
    }

    private String saveFile(MultipartFile file){
        if (file != null){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String resultFile = UUID.randomUUID().toString() + "__" + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + resultFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultFile;
        }
        return "";
    }



    @RequestMapping("/pro")
    public List<Products> getAllPro() {
        return productsRepo.findAll();
    }

    @RequestMapping("/gro")
    public List<Groups> getAllGro() {
        return groupsRepo.findAll();
    }

    @RequestMapping("/img")
    public List<Images> getAllImg() {
        return imagesRepo.findAll();
    }



    @RequestMapping(value = "/add/prod", method = RequestMethod.POST)
    public ResponseEntity<String> addProd(@RequestParam(name = "price") Float price,
                                          @RequestParam(name = "name") String name,
                                          @RequestParam(name = "description") String description,
                                          @RequestParam(name = "shortDesc") String shortDesc,
                                          @RequestParam(name = "volume") String volume,
                                          @RequestParam(name = "file") MultipartFile file,
                                          @RequestParam(name = "groupName") String groupName) {
        if (productsRepo.existsByName(name)) return NotFoundResult;
        Products product = new Products(price, name, description, shortDesc, volume, saveFile(file), groupName);
        productsRepo.save(product);
        return OkResult;
    }

    @RequestMapping(value = "/del/prod/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delProd(@PathVariable Long id) {
        if (productsRepo.existsById(id)) {
            productsRepo.delete(productsRepo.getOne(id));
            return OkResult;
        } else return NotFoundResult;
    }

    @RequestMapping(value = "/upd/prod/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> editProd(@RequestParam(name = "price") Float price,
                                           @RequestParam(name = "name") String name,
                                           @RequestParam(name = "description") String description,
                                           @RequestParam(name = "fullDesc") String fullDesc,
                                           @RequestParam(name = "volume") String volume,
                                           @RequestParam(name = "file", required = false) MultipartFile file,
                                           @RequestParam(name = "groupName") String groupName,
                                           @PathVariable Long id) {
        if (productsRepo.existsById(id)) {
            Products beforeProduct = productsRepo.getOne(id);
            beforeProduct.setName(name);

            if (file != null) beforeProduct.setImageFileName(saveFile(file));

            beforeProduct.setDescription(description);
            beforeProduct.setShortDesc(fullDesc);
            beforeProduct.setPrice(price);
            beforeProduct.setVolume(volume);
            beforeProduct.setGroupName(groupName);
            productsRepo.save(beforeProduct);
            return OkResult;
        } else return NotFoundResult;
    }




    @RequestMapping(value = "/add/gro", method = RequestMethod.POST)
    public ResponseEntity<String> addGroup(@RequestParam(name = "name") String name,
                                           @RequestParam(name = "file") MultipartFile file) throws IOException {
        if (groupsRepo.existsByName(name)) {
            return NotFoundResult;
        }
        Groups group = new Groups();
        group.setName(name);
        group.setImageFileName(saveFile(file));
        groupsRepo.save(group);
        return OkResult;
    }

    @RequestMapping(value = "/del/gro/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delGroup(@PathVariable Long id) {
        if (groupsRepo.existsById(id)) {
            groupsRepo.delete(groupsRepo.getOne(id));
            return OkResult;
        } else return NotFoundResult;
    }

    @RequestMapping(value = "/upd/gro/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> editGroup(@RequestParam("name") String name,
                                            @RequestParam(name = "file", required = false) MultipartFile file,
                                            @PathVariable Long id) {
        if (groupsRepo.existsById(id)) {
            var beforeGroup = groupsRepo.getOne(id);
            beforeGroup.setName(name);
            if (file != null) beforeGroup.setImageFileName(saveFile(file));
            groupsRepo.save(beforeGroup);
            return OkResult;
        } else return NotFoundResult;
    }



    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void addFoo(@RequestParam(name = "file", required = false) MultipartFile file, @RequestParam("name") String name) {

        if (file != null){
            System.out.println(file.getName());
            System.out.println("File Name ==> " + file.getOriginalFilename());
            System.out.println("name ==> " + name);


            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String resultFile = UUID.randomUUID().toString() + "__" + name + file.getOriginalFilename();

            try {
                file.transferTo(new File(uploadPath + "/" + resultFile));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
//            group.setImageFileName(resultFile);
        }
        else System.out.println("file is null");


    }



}

class Test{
    private String name;
    private MultipartFile file;

    @Value("${upload.path}")
    private String uploadPath;

    public Test() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) throws IOException {
        int i = 0;
        if (file != null){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String resultFile = UUID.randomUUID().toString() + "__" + file.getOriginalFilename();

            File newFile = new File(uploadPath + resultFile);
            file.transferTo(newFile);
            this.file = file;
        }
        this.file = file;
    }
}

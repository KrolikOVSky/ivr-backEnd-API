package it.donnamaria.controllers;

import it.donnamaria.models.Groups;
import it.donnamaria.repos.GroupsRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static it.donnamaria.Global.*;

@RestController
@RequestMapping("api/group")
public class GroupController {
    private final GroupsRepo groupsRepo;

    public GroupController(GroupsRepo groupsRepo) {
        this.groupsRepo = groupsRepo;
    }

    @GetMapping
    public List<Groups> getAllGro() {
        return groupsRepo.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> addGroup(@RequestParam(name = "name") String name,
                                           @RequestParam(name = "file") MultipartFile file) {
        if (groupsRepo.existsByName(name)) {
            return NotFoundResult;
        }
        Groups group = new Groups(name, saveFile(file));
        groupsRepo.save(group);
        return OkResult;
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delGroup(@PathVariable Long id) {
        if (groupsRepo.existsById(id)) {
            groupsRepo.delete(groupsRepo.getOne(id));
            return OkResult;
        } else return NotFoundResult;
    }

    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
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

}
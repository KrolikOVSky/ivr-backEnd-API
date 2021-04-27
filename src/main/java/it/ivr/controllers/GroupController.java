package it.ivr.controllers;

import it.ivr.models.Groups;
import it.ivr.repos.GroupsRepo;
import it.ivr.Global;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/group")
@AllArgsConstructor
public class GroupController {
    private final GroupsRepo groupsRepo;

    @GetMapping
    public List<Groups> getAllGro() {
        return groupsRepo.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<String> addGroup(@RequestParam(name = "name") String name,
                                           @RequestParam(name = "file") MultipartFile file) {
        if (groupsRepo.existsByName(name)) {
            return Global.NotFoundResult;
        }
        Groups group = new Groups(name, Global.saveFile(file));
        groupsRepo.save(group);
        return Global.OkResult;
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delGroup(@PathVariable Long id) {
        if (groupsRepo.existsById(id)) {
            groupsRepo.delete(groupsRepo.getOne(id));
            return Global.OkResult;
        } else return Global.NotFoundResult;
    }

    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> editGroup(@RequestParam("name") String name,
                                            @RequestParam(name = "file", required = false) MultipartFile file,
                                            @PathVariable Long id) {
        if (groupsRepo.existsById(id)) {
            var beforeGroup = groupsRepo.getOne(id);
            beforeGroup.setName(name);
            if (file != null) beforeGroup.setImageFileName(Global.saveFile(file));
            groupsRepo.save(beforeGroup);
            return Global.OkResult;
        } else return Global.NotFoundResult;
    }

}
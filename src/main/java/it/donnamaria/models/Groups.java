package it.donnamaria.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.util.UUID;

@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String imageFileName;
    private String groupUUID;

    public Groups(Long id, String name, String imageFileName, String groupUUID) {
        this.id = id;
        this.name = name;
        this.imageFileName = imageFileName;
        this.groupUUID = groupUUID;
    }

    public Groups() {
        this.id = 0L;
        this.name = "";
        this.imageFileName = "";
        this.groupUUID = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getGroupUUID() {
        return groupUUID;
    }

    public void setGroupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
    }

    @Override
    public boolean equals(Object object){
        Groups obj = (Groups) object;
        return this.id.equals(obj.getId()) && this.name.equals(obj.getName());
    }

}

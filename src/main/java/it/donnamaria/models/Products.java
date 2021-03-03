package it.donnamaria.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float price;
    private String name;
    private String description;
    private String shortDesc;
    private String imageFileName;
    private String volume;
    private String groupName;

    public Products(Long id, Float price, String name, String description, String shortDesc, String imageFileName, String volume, String groupName) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.shortDesc = shortDesc;
        this.imageFileName = imageFileName;
        this.volume = volume;
        this.groupName = groupName;
    }

    public Products(Float price, String name, String description, String shortDesc, String imageFileName, String volume, String groupName) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.shortDesc = shortDesc;
        this.imageFileName = imageFileName;
        this.volume = volume;
        this.groupName = groupName;
    }

    public Products() {
        this.id = 0L;
        this.price = 0F;
        this.name = "";
        this.description = "";
        this.shortDesc = "";
        this.imageFileName = "";
        this.volume = "";
        this.groupName = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

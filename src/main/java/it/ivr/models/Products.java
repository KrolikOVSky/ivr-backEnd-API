package it.ivr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ivr.services.Utilities;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Products {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private Float price;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private String shortDesc;
    @NonNull
    private String imageFileName;
    @NonNull
    private String volume;

    private String transLitName;


    @NonNull
    @ManyToOne
    @JsonIgnore
    private Groups relatedGroup;

    public Products(Float price, String name, String description, String shortDesc, String imageFileName, String volume, Groups relatedGroup) {
        this.price = price;
        this.name = name;
        this.description = description;
        this.shortDesc = shortDesc;
        this.imageFileName = imageFileName;
        this.volume = volume;
        this.transLitName = Utilities.convert(name);
        this.relatedGroup = relatedGroup;
    }

    public void setName(String name) {
        this.name = name;
        this.transLitName = Utilities.convert(this.name);
    }
}

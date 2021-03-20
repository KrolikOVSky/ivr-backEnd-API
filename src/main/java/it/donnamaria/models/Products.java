package it.donnamaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
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

    @NonNull
    @ManyToOne
    @JsonIgnore
    private Groups relatedGroup;
}

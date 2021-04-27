package it.ivr.models;

import it.ivr.services.Utilities;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Groups {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String imageFileName;
    private String transLitName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "relatedGroup")
    @Getter
    @Setter
    private List<Products> products;

    public Groups(String name, String imageFileName) {
        this.name = name;
        this.imageFileName = imageFileName;
        this.transLitName = Utilities.convert(name);
    }

    public void setName(String name) {
        this.name = name;
        this.transLitName = Utilities.convert(this.name);
    }
}

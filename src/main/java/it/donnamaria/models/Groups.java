package it.donnamaria.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Groups {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String imageFileName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "relatedGroup")
    @Getter
    @Setter
    private List<Products> products;
}

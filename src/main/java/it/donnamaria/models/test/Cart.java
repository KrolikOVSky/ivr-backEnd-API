package it.donnamaria.models.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @OneToMany
    @NonNull
    private List<Items> items = new ArrayList<>();
}

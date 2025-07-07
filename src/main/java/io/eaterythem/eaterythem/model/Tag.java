package io.eaterythem.eaterythem.model;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Recipe> recipes;
}
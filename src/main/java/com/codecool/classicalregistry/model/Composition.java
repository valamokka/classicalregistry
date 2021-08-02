package com.codecool.classicalregistry.model;


import com.codecool.classicalregistry.model.DTO.CompositionDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private String title;
    @ManyToOne
    private Composer composer;

    public Composition(String title, Composer composer) {
        this.title = title;
        this.composer = composer;
    }

    public CompositionDTO toDTO() {
        return new CompositionDTO(
                this.id,
                this.title,
                this.composer.getId()
        );
    }
}

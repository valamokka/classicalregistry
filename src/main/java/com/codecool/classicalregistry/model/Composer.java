package com.codecool.classicalregistry.model;


import com.codecool.classicalregistry.model.DTO.ComposerDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Composer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    private String name;
    private String nationality;
    private int yearOfBirth;

    @ManyToOne
    private ClassicalPeriod classicalPeriod;

    @JsonIgnore
    @OneToMany (mappedBy = "composer")
    private List<Composition> compositions;

    public Composer(String name, String nationality, int yearOfBirth, ClassicalPeriod classicalPeriod, List<Composition> compositions) {
        this.name = name;
        this.nationality = nationality;
        this.yearOfBirth = yearOfBirth;
        this.classicalPeriod = classicalPeriod;
        this.compositions = compositions;
    }

    public ComposerDTO toDTO() {
        return new ComposerDTO(
                this.id,
                this.name,
                this.nationality,
                this.yearOfBirth,
                this.classicalPeriod.getId()
        );
    }
}

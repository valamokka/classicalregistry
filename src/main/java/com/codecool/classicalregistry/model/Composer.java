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

   // @NotNull(message = "You have to provide a name for this composer!")
    private String name;

    //@NotNull(message = "You have to provide a nationality for this composer!")
    private String nationality;

   // @NotNull(message = "You have to provide a year of birth for this composer!")
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

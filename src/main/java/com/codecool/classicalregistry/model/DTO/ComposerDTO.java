package com.codecool.classicalregistry.model.DTO;


import com.codecool.classicalregistry.model.ClassicalPeriod;
import com.codecool.classicalregistry.model.Composer;
import com.codecool.classicalregistry.model.Composition;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComposerDTO {

    private long id;

    @NotNull(message = "You have to provide a name for this composer!")
    @Size(min = 1, message = "Name should be at least 1 character long")
    private String name;

    @NotNull(message = "You have to provide a nationality for this composer!")
    @Size(min = 1, message = "Nationality should be at least 1 character long")
    private String nationality;

    @NotNull(message = "You have to provide a year of birth for this composer!")
    @Max(value = 2021, message = "Year of birth cannot be in the future!")
    private int yearOfBirth;

    @NotNull(message = "You have to provide an ID of a classical period!")
    private long classicalPeriodId;

    public Composer toEntity(ClassicalPeriod classicalPeriod, List<Composition> compositions) {
        return new Composer(
                this.name,
                this.nationality,
                this.yearOfBirth,
                classicalPeriod,
                compositions
        );
    }
}

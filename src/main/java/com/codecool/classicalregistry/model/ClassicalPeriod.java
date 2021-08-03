package com.codecool.classicalregistry.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassicalPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonIgnore
    private long id;

    @NotNull (message = "You have to provide a name for the period!")
    private String name;

    @NotNull(message = "You have to provide when the period begun!")
    @Min(value = 0, message = "Value cannot be negative")
    @Max(value = 2021, message = "Beginning cannot be in the future!")
    private int yearOfBeginning;

    @NotNull (message = "You have to provide when the period ended!")
    @Min(value = 0, message = "Value cannot be negative")
    @Max(value = 2021, message = "End cannot be in the future!")
    private int yearOfEnd;

    @JsonIgnore
    @OneToMany (mappedBy = "classicalPeriod")
    private List<Composer> composers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClassicalPeriod)) return false;
        ClassicalPeriod that = (ClassicalPeriod) o;
        return yearOfBeginning == that.yearOfBeginning && yearOfEnd == that.yearOfEnd && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, yearOfBeginning, yearOfEnd);
    }
}

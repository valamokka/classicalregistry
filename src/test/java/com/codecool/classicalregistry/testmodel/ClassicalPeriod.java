package com.codecool.classicalregistry.testmodel;

import com.codecool.classicalregistry.model.Composer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ClassicalPeriod {

    private long id;
    private String name;
    private int yearOfBeginning;
    private int yearOfEnd;
    private List<Composer> composers;

    public ClassicalPeriod(long id, String name, int yearOfBeginning, int yearOfEnd, List<Composer> composers) {
        this.id = id;
        this.name = name;
        this.yearOfBeginning = yearOfBeginning;
        this.yearOfEnd = yearOfEnd;
        this.composers = composers;
    }
}

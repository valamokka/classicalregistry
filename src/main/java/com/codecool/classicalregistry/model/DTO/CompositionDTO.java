package com.codecool.classicalregistry.model.DTO;

import com.codecool.classicalregistry.model.Composer;
import com.codecool.classicalregistry.model.Composition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompositionDTO {

    private long id;
    @NotNull (message = "You have to provide a title for the composition!")
    @Size(min = 1, message = "Title should be at least 1 character long!")
    private String title;
    @NotNull (message = "You have to provide an ID of a composer for this composition!")
    private long composerId;

    public Composition toEntity(Composer composer) {
        return new Composition(
                this.title,
                composer
        );
    }

}

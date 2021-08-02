package com.codecool.classicalregistry.service;

import com.codecool.classicalregistry.dao.repository.ComposerRepository;
import com.codecool.classicalregistry.dao.repository.CompositionRepository;
import com.codecool.classicalregistry.exceptions.DatabaseIsEmptyException;
import com.codecool.classicalregistry.exceptions.EntityDependencyException;
import com.codecool.classicalregistry.exceptions.EntryNotFoundException;
import com.codecool.classicalregistry.model.Composition;
import com.codecool.classicalregistry.model.DTO.CompositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompositionService {

    @Autowired
    private CompositionRepository compositionRepository;
    @Autowired
    private ComposerRepository composerRepository;

    public List<CompositionDTO> listAllCompositions() {
        List<CompositionDTO> allCompositionsInDatabase = compositionRepository.findAll().stream().
                map(Composition::toDTO).
                collect(Collectors.toList());
        if (allCompositionsInDatabase.isEmpty()) {
            throw new DatabaseIsEmptyException();
        }
        return allCompositionsInDatabase;
    }

    public CompositionDTO getCompositionById(long id) {
        return compositionRepository.findById(id).
                orElseThrow(EntryNotFoundException::new).
                toDTO();
    }

    public void addComposition(CompositionDTO compositionDTO) {
        compositionRepository.save(compositionDTO.
                                    toEntity(composerRepository.findById(compositionDTO.getComposerId()).orElseThrow(EntityDependencyException::new)));
    }

    public void updateCompositionById(CompositionDTO compositionDTO, long id) {
        Composition composition = compositionDTO.toEntity(composerRepository.findById(compositionDTO.getComposerId()).orElseThrow(EntityDependencyException::new));
        composition.setId(id);
        compositionRepository.save(composition);
    }

    public void deleteCompositionById(long id) {
        compositionRepository.deleteById(id);
    }

}

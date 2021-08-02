package com.codecool.classicalregistry.service;


import com.codecool.classicalregistry.dao.repository.ClassicalPeriodRepository;
import com.codecool.classicalregistry.dao.repository.ComposerRepository;
import com.codecool.classicalregistry.dao.repository.CompositionRepository;
import com.codecool.classicalregistry.exceptions.DatabaseIsEmptyException;
import com.codecool.classicalregistry.exceptions.EntityDependencyException;
import com.codecool.classicalregistry.exceptions.EntryNotFoundException;
import com.codecool.classicalregistry.model.Composer;
import com.codecool.classicalregistry.model.Composition;
import com.codecool.classicalregistry.model.DTO.ComposerDTO;
import com.codecool.classicalregistry.model.DTO.CompositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComposerService {

    @Autowired
    private ComposerRepository composerRepository;
    @Autowired
    private ClassicalPeriodRepository classicalPeriodRepository;
    @Autowired
    private CompositionRepository compositionRepository;

    public List<ComposerDTO> listAllComposers() {
        List<ComposerDTO> allComposersInDatabase = composerRepository.findAll().stream().
                                                                                map(Composer::toDTO).
                                                                                collect(Collectors.toList());
        if(allComposersInDatabase.isEmpty()) {
            throw new DatabaseIsEmptyException();
        }
        return allComposersInDatabase;
    }

    public ComposerDTO getComposerById(long id) {
        return composerRepository.findById(id).
                orElseThrow(EntryNotFoundException::new).
                toDTO();
    }

    public void addComposer(ComposerDTO composerDTO) {
        composerRepository.save(composerDTO.
                toEntity(classicalPeriodRepository.
                        findById(composerDTO.getClassicalPeriodId()).orElseThrow(EntityDependencyException::new), new ArrayList<>()));
    }

    public void updateComposerById(ComposerDTO composerDTO, long id) {
        Composer composer = composerDTO.
                toEntity(classicalPeriodRepository.findById(composerDTO.getClassicalPeriodId()).orElseThrow(EntityDependencyException::new),
                        compositionRepository.findAllCompositionsByComposerId(id)
                );
        composer.setId(id);
        composerRepository.save(composer);
    }

    public void deleteComposerById(long id) {
        composerRepository.deleteById(id);
    }

    public List<CompositionDTO> findAllCompositionsByComposerId(long id) {
        return compositionRepository.
                findAllCompositionsByComposerId(id).
                stream().
                map(Composition::toDTO).
                collect(Collectors.toList());
    }
}

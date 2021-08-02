package com.codecool.classicalregistry.dao.repository;

import com.codecool.classicalregistry.model.Composition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionRepository extends JpaRepository<Composition, Long> {

    List<Composition> findAllCompositionsByComposerId(long id);
}

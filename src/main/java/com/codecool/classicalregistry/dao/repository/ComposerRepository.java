package com.codecool.classicalregistry.dao.repository;

import com.codecool.classicalregistry.model.Composer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComposerRepository extends JpaRepository<Composer, Long> {
}

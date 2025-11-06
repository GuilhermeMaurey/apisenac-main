package com.example.apisenac.repository;

import com.example.apisenac.model.Lingua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinguaRepository extends JpaRepository<Lingua, Integer> {
}

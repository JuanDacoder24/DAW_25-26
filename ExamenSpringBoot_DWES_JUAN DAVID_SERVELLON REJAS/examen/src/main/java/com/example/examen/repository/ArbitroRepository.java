package com.example.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.entity.Arbitro;

@Repository
public interface ArbitroRepository extends JpaRepository<Arbitro, String>{

}

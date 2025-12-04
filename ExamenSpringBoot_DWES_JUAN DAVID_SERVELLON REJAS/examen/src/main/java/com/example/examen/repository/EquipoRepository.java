package com.example.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.entity.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{

}

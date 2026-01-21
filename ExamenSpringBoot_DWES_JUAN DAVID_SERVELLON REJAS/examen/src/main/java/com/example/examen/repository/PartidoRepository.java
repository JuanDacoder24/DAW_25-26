package com.example.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.entity.Partido;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long>{

}

package com.example.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examen.entity.Equipo;
import com.example.examen.entity.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long>{

    List<Jugador> findByEquipo(Equipo equipo);


}

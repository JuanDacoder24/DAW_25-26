package com.ejemplo.morosos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.morosos.entity.Moroso;

@Repository
public interface MorosoRepository extends JpaRepository<Moroso, Long>{

}

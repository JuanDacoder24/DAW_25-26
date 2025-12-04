package com.example.simulacro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simulacro.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, String>{

    List<Empleado> findByEmpresaId(Long empresaId);

}

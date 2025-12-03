package com.example.simulacro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{

    List<Empleado> findByEmpresaId(Long empresaId);

    

}

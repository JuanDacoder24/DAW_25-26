package com.example.simulacro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.simulacro.entity.Empleado;
import com.example.simulacro.repository.EmpleadoRepository;
import com.example.simulacro.repository.EmpresaRepository;
import com.example.simulacro.services.EmpresaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    private EmpresaService service;

    private final EmpleadoRepository empleadoRepository;
    private final EmpresaRepository empresaRepository;

    public EmpleadoController(EmpleadoRepository empleadoRepository, EmpresaRepository empresaRepository){
        this.empleadoRepository = empleadoRepository;
        this.empresaRepository = empresaRepository;
    }

    //Devuelve una lista con los nombres de los empleados 
    @GetMapping()
    public List<String> getListaEmpleados(){
        return service.getListaEmpleados();
    }
    

    //Devuelve todos los productos como objeto
    @GetMapping("/registros")
    public List<Empleado> getAllRegistros(){
        return service.getAllEmpleados();
    }
    
    
    

}

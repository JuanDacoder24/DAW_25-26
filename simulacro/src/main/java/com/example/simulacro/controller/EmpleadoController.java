package com.example.simulacro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.simulacro.entity.Empleado;
import com.example.simulacro.repository.EmpleadoRepository;
import com.example.simulacro.repository.EmpresaRepository;
import com.example.simulacro.services.EmpresaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
public class EmpleadoController {

    @Autowired
    private EmpresaService service;

    //Devuelve una lista con los nombres de los empleados 
    @GetMapping()
    public List<String> getListaEmpleados() {
        return service.getListaEmpleados();
    }

    //Devuelve todos los productos como objeto
    @GetMapping("/registros")
    public List<Empleado> getAllRegistros() {
        return service.getAllEmpleados();
    }

    //Devuelve los datos referentes a un empleado en concreto
    @GetMapping("/empleado")
    public List<Empleado> getRegistroEmpledao(@RequestParam String nombreEmpleado) {
        return service.getRegistrarEmpleado(nombreEmpleado);
    }

    //Añadir empleado
    @PostMapping()
    public void postEmpleado(@RequestParam Empleado empleado) {
        empleado.setId("0L");
        service.addEmpleado(empleado);
    }

    @PutMapping("/{id}/actualizarnombre")
    public void actualizarNombre(@PathVariable Long id, @RequestParam String nombre) {
        service.actualizarNombre(nombre, id);
    }

    @DeleteMapping("/{id}/eliminarregistro")
    public void eliminarRegistro(@PathVariable Long id) {
        service.eliminarRegistro(id);
    }

}

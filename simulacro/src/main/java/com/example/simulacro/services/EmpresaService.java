package com.example.simulacro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.simulacro.entity.Empleado;
import com.example.simulacro.repository.EmpleadoRepository;

@Service
public class EmpresaService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    //Devuelve una lista con el nombre de los empleados
    public List<String> getListaEmpleados(){
        return empleadoRepository.findAll().stream().map(Empleado::getNombre).distinct().toList();
    }

    //Devuelve todos los empleados 
    public List<Empleado> getAllEmpleados(){
        return empleadoRepository.findAll();
    }

    //Devuelve una lista con los datos de un empleado a traves de un filtro por nombre del empleado
    public List<Empleado> getRegistrarEmpleado(String nombreEmpleado){
        return empleadoRepository.findAll().stream().filter(empleado -> empleado.getNombre().equalsIgnoreCase(nombreEmpleado)).toList();
    }

    //Añade empleado
    public void addEmpleado(Empleado empleado){
        if(empleado != null){
            empleadoRepository.save(empleado);
        }
    }

    //Actualizar nombre de empleado
    public void actualizarNombre(String nombre, Long id){
        Empleado empleado = empleadoRepository.findById(id).orElse(null);
        empleado.setNombre(nombre);
        empleadoRepository.save(empleado);
    }

    //Eliminar producto por id
    public void eliminarRegistro(Long id){
        empleadoRepository.deleteById(id);
    }





}

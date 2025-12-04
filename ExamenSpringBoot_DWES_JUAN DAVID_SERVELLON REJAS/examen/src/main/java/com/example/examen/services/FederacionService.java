package com.example.examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examen.entity.Arbitro;
import com.example.examen.entity.Equipo;
import com.example.examen.entity.Jugador;
import com.example.examen.repository.ArbitroRepository;
import com.example.examen.repository.EquipoRepository;
import com.example.examen.repository.JugadorRepository;

@Service
public class FederacionService {

    //Inyeccion de repositorios 
    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private ArbitroRepository arbitroRepository;


    //Devuelve todos los jugadores
    public List<Jugador> getAllJugadores(){
        return jugadorRepository.findAll();
    }

    //Devuelve todos los equipos
    public List<Equipo> getAllEquipos(){
        return equipoRepository.findAll();
    }

    //Devuelve todos los aribtros
    public List<Arbitro> getAllArbitros(){
        return arbitroRepository.findAll();
    }



    //Añade jugador
    public void addJugador(Jugador jugador) {
        if (jugador != null) {
            jugadorRepository.save(jugador);
        }
    }

    //Añade equipo
    public void addEquipo(Equipo equipo) {
        if (equipo != null) {
            equipoRepository.save(equipo);
        }
    }

    //Añade arbitro
    public void addArbitro(Arbitro arbitro) {
        if (arbitro != null) {
            arbitroRepository.save(arbitro);
        }
    }


    //Actualizar nombre del jugador 
    public boolean actualizarNombreJugador(String nombre, String id) {
        Jugador jugador = jugadorRepository.findById(id).orElse(null);
        if (jugador == null) {
            return false; // No se encontro
        }
        jugador.setNombre(nombre);
        jugadorRepository.save(jugador);
        return true; // Actualizado correctamente
    }

    //Actualizar nombre del equipo 
    public boolean actualizarNombreEquipo(String nombre_equipo, int id) {
        Equipo equipo = equipoRepository.findById(id).orElse(null);
        if (equipo == null) {
            return false; // No se encontro
        }
        //equipo.getNombre_equipo(nombre_equipo);
        equipoRepository.save(equipo);
        return true; // Actualizado correctamente
    }



    //Eliminar jugador
    public boolean eliminarJugador(String id) {
        if (!jugadorRepository.existsById(id)) {
            return false; // No existe
        }
        jugadorRepository.deleteById(id);
        return true; // Eliminado correctamente
    }

    //Eliminar arbitro
    public boolean eliminarArbitro(String id) {
        if (!arbitroRepository.existsById(id)) {
            return false; // No existe
        }
        arbitroRepository.deleteById(id);
        return true; // Eliminado correctamente
    }

    //Eliminar equipo
    public boolean eliminarEquipo(int id) {
        if (!equipoRepository.existsById(id)) {
            return false; // No existe
        }
        equipoRepository.deleteById(id);
        return true; // Eliminado correctamente
    }








    //lista de jugadores por nombre, por que con equipos no se como hacerlo
    public List<Jugador> getJugadorEquipo(String nombreJugador) {
        return jugadorRepository.findAll().stream().filter(jugador -> jugador.getNombre().equalsIgnoreCase(nombreJugador)).toList();
    }

    //Aca lo que yo quiero hacer es un metodo para devolver lista de arbitro segun su rol, esta comentado por que me da error 
    // public List<Arbitro> getListarArbitroRol(String rol) {
    //     return arbitroRepository.findAll().stream().filter(arbitro -> arbitro.getRol()).toList();
    // }



}

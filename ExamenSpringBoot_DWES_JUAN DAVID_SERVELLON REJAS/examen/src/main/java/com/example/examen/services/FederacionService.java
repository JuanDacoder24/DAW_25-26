package com.example.examen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.examen.entity.Arbitro;
import com.example.examen.entity.Equipo;
import com.example.examen.entity.Jugador;
import com.example.examen.entity.Partido;
import com.example.examen.entity.Rol;
import com.example.examen.repository.ArbitroRepository;
import com.example.examen.repository.EquipoRepository;
import com.example.examen.repository.JugadorRepository;
import com.example.examen.repository.PartidoRepository;

@Service
public class FederacionService {

    //Inyeccion de repositorios 
    @Autowired
    private JugadorRepository jugadorRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private ArbitroRepository arbitroRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    // EQUIPOS
    public List<Equipo> getAllEquipos(){
        return equipoRepository.findAll();
    }

    public Equipo getEquipoById(Long id){
        return equipoRepository.findById(id).orElse(null);
    }

    public void addEquipo(Equipo equipo) {
        if (equipo != null) {
            equipoRepository.save(equipo);
        }
    }

    public boolean actualizarNombreEquipo(String nombre_equipo, Long id) {
        Equipo equipo = equipoRepository.findById(id).orElse(null);
        if (equipo == null) return false;
        equipo.setNombre_equipo(nombre_equipo);
        equipoRepository.save(equipo);
        return true;
    }

    public boolean eliminarEquipo(Long id) {
        if (!equipoRepository.existsById(id)) return false;
        equipoRepository.deleteById(id);
        return true;
    }

    // JUGADORES
    public List<Jugador> getAllJugadores(){
        return jugadorRepository.findAll();
    }

    public List<Jugador> getJugadoresPorEquipo(Equipo equipo){
        return jugadorRepository.findByEquipo(equipo);
    }

    public void addJugador(Jugador jugador) {
        if (jugador != null) {
            jugadorRepository.save(jugador);
        }
    }

    public boolean actualizarEquipoJugador(Long jugadorId, Long equipoId) {
        Jugador jugador = jugadorRepository.findById(jugadorId).orElse(null);
        if (jugador == null) return false;
        Equipo equipo = equipoRepository.findById(equipoId).orElse(null);
        if (equipo == null) return false;
        jugador.setEquipo(equipo);
        jugadorRepository.save(jugador);
        return true;
    }

    public boolean eliminarJugador(Long id) {
        if (!jugadorRepository.existsById(id)) return false;
        jugadorRepository.deleteById(id);
        return true;
    }

    // ÁRBITROS
    public List<Arbitro> getAllArbitros(){
        return arbitroRepository.findAll();
    }

    public List<Arbitro> getArbitrosPorRol(Rol rol) {
        return arbitroRepository.findAll().stream()
                .filter(arbitro -> arbitro.getRol() == rol)
                .toList();
    }

    public void addArbitro(Arbitro arbitro) {
        if (arbitro != null) {
            arbitroRepository.save(arbitro);
        }
    }

    public boolean eliminarArbitro(Long id) {
        if (!arbitroRepository.existsById(id)) return false;
        arbitroRepository.deleteById(id);
        return true;
    }

    public List<Jugador> getJugadorPorNombre(String nombreJugador) {
        return jugadorRepository.findAll().stream()
                .filter(jugador -> jugador.getNombre().equalsIgnoreCase(nombreJugador))
                .toList();
    }

    // PARTIDOS
    public List<Partido> getAllPartidos(){
        return partidoRepository.findAll();
    }

    public Partido getPartidoById(Long id){
        return partidoRepository.findById(id).orElse(null);
    }

    public void addPartido(Partido partido) {
        if (partido != null) {
            partidoRepository.save(partido);
        }
    }

    public boolean eliminarPartido(Long id) {
        if (!partidoRepository.existsById(id)) return false;
        partidoRepository.deleteById(id);
        return true;
    }



}

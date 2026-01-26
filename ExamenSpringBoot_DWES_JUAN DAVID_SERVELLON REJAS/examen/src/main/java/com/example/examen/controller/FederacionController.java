package com.example.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.examen.entity.Arbitro;
import com.example.examen.entity.Equipo;
import com.example.examen.entity.Jugador;
import com.example.examen.entity.Partido;
import com.example.examen.entity.Rol;
import com.example.examen.services.FederacionService;


@RestController
@RequestMapping("/api")
public class FederacionController {

    @Autowired
    private FederacionService service;

    // ===== EQUIPOS =====
    // Listado de equipos
    @GetMapping("/equipos")
    public List<Equipo> getListaEquipos() {
        return service.getAllEquipos();
    }

    // Agregar equipo por ID
    @PostMapping("/equipos")
    public ResponseEntity<String> addEquipo(@RequestBody Equipo equipo){
        service.addEquipo(equipo);
        return ResponseEntity.ok("Equipo agregado correctamente");
    }

    // Actualizar nombre del equipo
    @PutMapping("/equipos/{id}")
    public ResponseEntity<String> actualizarEquipo(@PathVariable Long id, @RequestParam String nombre) {
        boolean actualizado = service.actualizarNombreEquipo(nombre, id);
        if (actualizado) {
            return ResponseEntity.ok("Equipo actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //eliminar equipo
    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<String> eliminarEquipo(@PathVariable Long id){
        boolean eliminado = service.eliminarEquipo(id);
        if (eliminado) {
            return ResponseEntity.ok("Equipo eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ===== JUGADORES =====
    // Listado de jugadores
    @GetMapping("/jugadores")
    public List<Jugador> getListaJugadores() {
        return service.getAllJugadores();
    }

    // Obtener jugadores por nombre
    @GetMapping("/jugadores/nombre/{nombre}")
    public List<Jugador> getJugadoresPorNombre(@PathVariable String nombre) {
        return service.getJugadorPorNombre(nombre);
    }

    // Obtener jugadores por equipo
    @GetMapping("/equipos/{equipoId}/jugadores")
    public ResponseEntity<List<Jugador>> getJugadoresPorEquipo(@PathVariable Long equipoId) {
        Equipo equipo = service.getEquipoById(equipoId);
        if (equipo == null) {
            return ResponseEntity.notFound().build();
        }
        List<Jugador> jugadores = service.getJugadoresPorEquipo(equipo);
        return ResponseEntity.ok(jugadores);
    }

    //agregar jugador
    @PostMapping("/jugadores")
    public ResponseEntity<String> addJugador(@RequestBody Jugador jugador){
        service.addJugador(jugador);
        return ResponseEntity.ok("Jugador agregado correctamente");
    }

    // Actualizar equipo del jugador
    @PutMapping("/jugadores/{jugadorId}/equipo/{equipoId}")
    public ResponseEntity<String> actualizarEquipoJugador(@PathVariable Long jugadorId, @PathVariable Long equipoId) {
        boolean actualizado = service.actualizarEquipoJugador(jugadorId, equipoId);
        if (actualizado) {
            return ResponseEntity.ok("Equipo del jugador actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //eliminar jugador
    @DeleteMapping("/jugadores/{id}")
    public ResponseEntity<String> eliminarJugador(@PathVariable Long id){
        boolean eliminado = service.eliminarJugador(id);
        if (eliminado) {
            return ResponseEntity.ok("Jugador eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ===== ÁRBITROS =====
    // Listado de árbitros
    @GetMapping("/arbitros")
    public List<Arbitro> getListaArbitros() {
        return service.getAllArbitros();
    }

    // Obtener árbitros por rol
    @GetMapping("/arbitros/rol/{rol}")
    public List<Arbitro> getArbitrosPorRol(@PathVariable Rol rol) {
        return service.getArbitrosPorRol(rol);
    }

    //agregar árbitro
    @PostMapping("/arbitros")
    public ResponseEntity<String> addArbitro(@RequestBody Arbitro arbitro){
        service.addArbitro(arbitro);
        return ResponseEntity.ok("Árbitro agregado correctamente");
    }

    //eliminar árbitro
    @DeleteMapping("/arbitros/{id}")
    public ResponseEntity<String> eliminarArbitro(@PathVariable Long id){
        boolean eliminado = service.eliminarArbitro(id);
        if (eliminado) {
            return ResponseEntity.ok("Árbitro eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ===== PARTIDOS =====
    
    @GetMapping("/partidos")
    public List<Partido> getListaPartidos() {
        return service.getAllPartidos();
    }

    @GetMapping("/partidos/{id}")
    public ResponseEntity<Partido> getPartidoById(@PathVariable Long id) {
        Partido partido = service.getPartidoById(id);
        if (partido != null) {
            return ResponseEntity.ok(partido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/partidos")
    public ResponseEntity<String> addPartido(@RequestBody Partido partido){
        service.addPartido(partido);
        return ResponseEntity.ok("Partido creado correctamente");
    }

    @DeleteMapping("/partidos/{id}")
    public ResponseEntity<String> eliminarPartido(@PathVariable Long id){
        boolean eliminado = service.eliminarPartido(id);
        if (eliminado) {
            return ResponseEntity.ok("Partido eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

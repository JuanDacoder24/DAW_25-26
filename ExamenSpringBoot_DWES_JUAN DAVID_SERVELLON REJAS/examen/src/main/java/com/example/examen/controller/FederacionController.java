package com.example.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.examen.entity.Arbitro;
import com.example.examen.entity.Equipo;
import com.example.examen.entity.Jugador;
import com.example.examen.repository.JugadorRepository;
import com.example.examen.services.FederacionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
public class FederacionController {

    //Inyeccion del servicio
    @Autowired
    private FederacionService service;

    @Autowired
    private JugadorRepository jugadorRepository;

    //Devuelve una lista de jugadores 
    @GetMapping("/jugadores")
    public List<Jugador> getListaJugadores() {
        return service.getAllJugadores();
    }

    //Devuelve una lista de equipos
    @GetMapping("/equipos")
    public List<Equipo> getListaEquipos() {
        return service.getAllEquipos();
    }

    //Devuelve una lista de arbitros
    @GetMapping("/arbitros")
    public List<Arbitro> getListaArbitros() {
        return service.getAllArbitros();
    }
    
    //Lista jugadores por equipo
    @GetMapping("/equipos/{equipoId}/jugadores")
    public ResponseEntity<List<Jugador>> getJugadoresPorEquipo(@PathVariable int equipoId) {

        List<Jugador> jugadores = jugadorRepository.findByEquipoId(equipoId);
        return ResponseEntity.ok(jugadores);
    }
    

    //Añadir jugador
    @PostMapping("/addjugador")
    public void postJugador(@RequestParam Jugador jugador){
        jugador.setId("");
        service.addJugador(jugador);
    }

    //Añadir equipo
    @PostMapping("/addequipo")
    public void postEquipo(@RequestParam Equipo equipo){
        equipo.setId(0);
        service.addEquipo(equipo);
    }

    //Añadir arbitro
    @PostMapping("/addarbitro")
    public void postEquipo(@RequestParam Arbitro arbitro){
        arbitro.setId("");
        service.addArbitro(arbitro);
    }
    

    //Acualizar nombre de equipo
    @PutMapping("/{id}/actualizarnombre")
    public void actualizarNombreEquipo(@PathVariable int id, @RequestParam String nombre) {
        service.actualizarNombreEquipo(nombre, id);
    }

    //Eliminar equipo
    @DeleteMapping("/{id}/eliminarequipo")
    public void eliminarEquipo(@PathVariable int id){
        service.eliminarEquipo(id);
    }

}

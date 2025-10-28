package com.example.gympro.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/entrenamientos")
    public String mostrarEntrenamientos() {
        return "entrenamientos"; 
    }

    @GetMapping("/nutricion")
    public String mostrarNutricion() {
        return "nutricion"; 
    }

    @GetMapping("/reservas")
    public String mostrarReservas() {
        return "reservas"; 
    }
}

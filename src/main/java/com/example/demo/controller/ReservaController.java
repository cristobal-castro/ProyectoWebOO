package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.demo.model.Cancha;
import com.example.demo.model.Reserva;
import com.example.demo.services.CanchaServiceImplementation;
import com.example.demo.services.ReservaServiceImplementation;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired 
    private ReservaServiceImplementation reservaService;

    @Autowired 
    private CanchaServiceImplementation canchaService;


    @GetMapping("/lista")
    public String lista( Model model){
        List<Reserva> reservas =  reservaService.listAll();
        List<Cancha> canchas = canchaService.listAll(); 
        model.addAttribute("canchas", canchas);
        model.addAttribute("reserva", new Reserva());
        return "reservas/lista";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute Reserva reserva ,Model model) {
        reservaService.saveReserva(reserva);

        model.addAttribute("reservas", reservaService.listAll());
        model.addAttribute("reserva", new Reserva());
        return "reservas/lista";
    }


    
}

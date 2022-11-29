package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.example.demo.model.Cancha;
import com.example.demo.model.Reserva;
import com.example.demo.services.CanchaServiceImplementation;
import com.example.demo.services.ReservaServiceImplementation;
import java.util.ArrayList;
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
        model.addAttribute("horario", getHorario());
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
        model.addAttribute("reservaActive", "active");
        return "reservas/lista";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute Reserva reserva ,Model model) {
        reservaService.saveReserva(reserva);
        List<Cancha> canchas = canchaService.listAll(); 
        List<Reserva> reservas =  reservaService.listAll();
        model.addAttribute("canchas", canchas);
        model.addAttribute("horario", getHorario());
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
        model.addAttribute("reservaActive", "active");
        return "reservas/lista";
    }
    
    @GetMapping("/mis-reservas")
    public String misReservas(Model model) { 
        List<Reserva> reservas =  reservaService.listAll();
        model.addAttribute("reservas", reservas);
        model.addAttribute("reservaActive", "active");
        return "reservas/mis-reservas";
    }
        
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id,  Model model) { 
        reservaService.deleteReserva(id);
        List<Reserva> reservas =  reservaService.listAll();
        model.addAttribute("reservas", reservas);
        model.addAttribute("reservaActive", "active");

        return "reservas/mis-reservas";
    }

    public List<String[]> getHorarioResevas(List<Reserva> reservas, List<Cancha>canchas){
        List<String[]> lista = new ArrayList<>();
        for(Cancha cancha:canchas){
            List<String> reservasCancha = new ArrayList<>();
            for (Reserva reserva : reservas) {
                    if(reserva.getCancha().getId() == cancha.getId()){
                        reservasCancha.add(reserva.getHoraInicio());
                    }
            }
            String[] arr = new String[reservasCancha.size()];
            lista.add(reservasCancha.toArray(arr));
        }
        return lista;
    }

    public List<String> getHorario(){
        List<String> lista = new ArrayList<>();
        lista.add("08:00");
        lista.add("09:20");
        lista.add("10:40");
        lista.add("12:00");
        lista.add("13:20");
        lista.add("14:40");
        lista.add("15:20");
        lista.add("16:00");
        lista.add("17:20");
        lista.add("18:40");
        lista.add("20:20");
        return lista;
    }


    
}

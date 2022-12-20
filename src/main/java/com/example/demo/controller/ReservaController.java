package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.example.demo.model.Cancha;
import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.services.CanchaServiceImplementation;
import com.example.demo.services.ReservaServiceImplementation;
import com.example.demo.services.UsuarioServiceImplementation;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaServiceImplementation reservaService;

    @Autowired
    private CanchaServiceImplementation canchaService;

    @Autowired
    private UsuarioServiceImplementation usuarioService;

    @GetMapping("/lista")
    public String lista(Model model) {
        Calendar c = Calendar.getInstance();
        String startDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH) + 1)+"/"+c.get(Calendar.DATE);
        Date date = new Date(startDate);
        List<Reserva> reservas= reservaService.filter(date);
        List<Cancha> canchas = canchaService.listHabilitadas("Habilitada");
        model.addAttribute("canchas", canchas);
        model.addAttribute("horario", getHorario());
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
        model.addAttribute("reservaActive", "active");
        model.addAttribute("fecha", date);
        return "reservas/lista";
    }

    @PostMapping("/agregar/{fecha}/{cancha}/{hora}")
    public String agregar( Model model,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
        @PathVariable int cancha,
        @PathVariable String hora,
        @CookieValue(value = "id", defaultValue = "-1") Integer idUser
        ) {
        Usuario user = usuarioService.getById(idUser);
        Reserva reserva = new Reserva();
        if(user!=null){
            reserva.setCancha(canchaService.getById(cancha));
            reserva.setFecha(fecha);
            reserva.setHoraInicio(hora);
            reserva.setUsuario(user);
            reservaService.saveReserva(reserva);
            System.out.println("Funca hasta aca");
            List<Cancha> canchas = canchaService.listHabilitadas("Habilitada");
            List<Reserva> reservas = reservaService.filter(reserva.getFecha());
            model.addAttribute("canchas", canchas);
            model.addAttribute("horario", getHorario());
            model.addAttribute("reserva", new Reserva());
            model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
            model.addAttribute("reservaActive", "active");
            model.addAttribute("fecha", reserva.getFecha());
        }

        return "reservas/lista";
    }

    @GetMapping("/mis-reservas")
    public String misReservas(Model model) {
        List<Reserva> reservas = reservaService.listAll();
        model.addAttribute("reservas", reservas);
        model.addAttribute("reservaActive", "active");
        return "reservas/mis-reservas";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable Integer id, Model model) {
        reservaService.deleteReserva(id);
        List<Reserva> reservas = reservaService.listAll();
        model.addAttribute("reservas", reservas);
        model.addAttribute("reservaActive", "active");

        return "reservas/mis-reservas";
    }

    @GetMapping("/lista/{fecha}")
    public String filter(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, Model model) {
        List<Reserva> reservas= reservaService.filter(fecha);
        List<Cancha> canchas = canchaService.listHabilitadas("Habilitada");
        model.addAttribute("canchas", canchas);
        model.addAttribute("horario", getHorario());
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
        model.addAttribute("reservaActive", "active");
        model.addAttribute("fecha", fecha);
        return "reservas/lista";
    }

    public List<String[]> getHorarioResevas(List<Reserva> reservas, List<Cancha> canchas) {
        List<String[]> lista = new ArrayList<>();
        for (Cancha cancha : canchas) {
            List<String> reservasCancha = new ArrayList<>();
            for (Reserva reserva : reservas) {
                if (reserva.getCancha().getId() == cancha.getId()) {
                    reservasCancha.add(reserva.getHoraInicio());
                }
            }
            String[] arr = new String[reservasCancha.size()];
            lista.add(reservasCancha.toArray(arr));
        }
        return lista;
    }

    public List<String> getHorario() {
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

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.example.demo.model.Cancha;
import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.services.CanchaServiceImplementation;
import com.example.demo.services.EmailServiceImplementation;
import com.example.demo.services.ReservaServiceImplementation;
import com.example.demo.services.UsuarioServiceImplementation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/reserva")
public class ReservaController {
    @Autowired
    private ReservaServiceImplementation reservaService;
    @Autowired
    private CanchaServiceImplementation canchaService;
    @Autowired
	private UsuarioServiceImplementation usuarioService;
    @Autowired
    private EmailServiceImplementation emailService;

    @GetMapping("/lista")
    public String lista(
            @CookieValue(value = "id", defaultValue="0") String id,
            @CookieValue(value = "rol", defaultValue="0") String rol,
            Model model){
        Usuario usuario = usuarioService.getById(Integer.parseInt(id));
        Calendar c = Calendar.getInstance();
        String startDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH) + 1)+"/"+c.get(Calendar.DATE);
        Date date = new Date(startDate);
        List<Reserva> reservas =  reservaService.filter(date);
        List<Cancha> canchas = canchaService.listAll(); 
        model.addAttribute("canchas", canchas);
        model.addAttribute("horario", getHorario());
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
        model.addAttribute("reservaActive", "active");
        model.addAttribute("fecha", date);
    
        model.addAttribute("id", id);
        model.addAttribute("rol", rol);
        model.addAttribute("usuario", usuario);

        return "reservas/lista";
    }

    @PostMapping("/agregar/{fecha}/{cancha}/{hora}")
    public String agregar( Model model,
        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
        @PathVariable int cancha,
        @PathVariable String hora,
        @CookieValue(value = "id", defaultValue="0") String id,
        @CookieValue(value = "rol", defaultValue="0") String rol
        ) {
        
        Usuario user = usuarioService.getById(Integer.parseInt(id));
        Reserva reserva = new Reserva();
        if(user!=null){
            reserva.setCancha(canchaService.getById(cancha));
            reserva.setFecha(fecha);
            reserva.setHoraInicio(hora);
            reserva.setUsuario(user);
            Reserva a = reservaService.saveReserva(reserva);
            user.addReservas(reserva);
            usuarioService.saveUsuario(user);
            List<Cancha> canchas = canchaService.listHabilitadas("Habilitada");
            List<Reserva> reservas = reservaService.filter(reserva.getFecha());
            model.addAttribute("canchas", canchas);
            model.addAttribute("horario", getHorario());
            model.addAttribute("reserva", new Reserva());
            model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
            model.addAttribute("reservaActive", "active");
            model.addAttribute("fecha", reserva.getFecha());

            emailService.sendEmailReserva(user, fecha, hora, cancha);
        }
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", user);

        return "reservas/lista";
    }



    @GetMapping("/mis-reservas")
    public String misReservas(
            @CookieValue(value = "id", defaultValue="0") String id,
            @CookieValue(value = "rol", defaultValue="0") String rol,
            Model model) {
        Usuario user = usuarioService.getById(Integer.parseInt(id));
        List<Reserva> reservas = user.getReservas();
        System.out.println("MIS PARTIDAS::"+ user.getPartidas().size());
        model.addAttribute("reservas", reservas);

        model.addAttribute("reservaActive", "active");

        Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id));
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);

        return "reservas/mis-reservas";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(
            @CookieValue(value = "id", defaultValue="0") String id_cookie,
            @CookieValue(value = "rol", defaultValue="0") String rol, 
            @PathVariable Integer id,  
            Model model) {

        reservaService.deleteReserva(id);
  

        Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id_cookie));
		model.addAttribute("id", id_cookie);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
        List<Reserva> reservas = usuario.getReservas();
        model.addAttribute("reservas", reservas);
        model.addAttribute("reservaActive", "active");
        


        return "reservas/mis-reservas";
    }

    @GetMapping("/lista/{fecha}")
    public String filter(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @CookieValue(value = "id", defaultValue="0") String id_cookie,
            @CookieValue(value = "rol", defaultValue="0") String rol,  
            Model model) {

        List<Reserva> reservas= reservaService.filter(fecha);
        List<Cancha> canchas = canchaService.listHabilitadas("Habilitada");
        model.addAttribute("canchas", canchas);
        model.addAttribute("horario", getHorario());
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
        model.addAttribute("reservaActive", "active");
        model.addAttribute("fecha", fecha);

        Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id_cookie));
		model.addAttribute("id", id_cookie);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
        
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

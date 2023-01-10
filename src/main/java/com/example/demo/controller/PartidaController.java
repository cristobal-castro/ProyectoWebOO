package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.Cancha;
import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.services.CanchaServiceImplementation;
import com.example.demo.services.ReservaServiceImplementation;
import com.example.demo.services.UsuarioServiceImplementation;

@RequestMapping("/partida")
@Controller
public class PartidaController {
    
    @Autowired
    private ReservaServiceImplementation reservaService;

    @Autowired
    private CanchaServiceImplementation canchaService;
    @Autowired
	private UsuarioServiceImplementation usuarioService;


    

    @GetMapping("/lista")
    public String listas(
        Model model,
        @CookieValue(value = "id", defaultValue = "0") String id_cookie,
        @CookieValue(value = "rol", defaultValue = "0") String rol
        ){
        return "partida/lista";
    }


    @GetMapping("/reservar")
    public String crear(
        Model model, 
        Reserva reserva,
        @CookieValue(value = "id", defaultValue = "0") String id_cookie,
        @CookieValue(value = "rol", defaultValue = "0") String rol
        ){
        model.addAttribute("partida", reserva);

        Calendar c = Calendar.getInstance();
        String startDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH) + 1)+"/"+c.get(Calendar.DATE);
        Date date = new Date(startDate);
        List<Reserva> reservas =  reservaService.listAll();
        List<Cancha> canchas = canchaService.listAll(); 
        model.addAttribute("canchas", canchas);
        model.addAttribute("horario", getHorario());
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
        model.addAttribute("reservaActive", "active");
        model.addAttribute("fecha", date);
        Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id_cookie));
		model.addAttribute("id", id_cookie);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
        return "partida/listaHora";
    }
    @GetMapping("/listaHora")
    public String lista(
        Model model, 
        Reserva reserva,
        @CookieValue(value = "id", defaultValue = "0") String id_cookie,
        @CookieValue(value = "rol", defaultValue = "0") String rol
        ){
        Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id_cookie));
		model.addAttribute("id", id_cookie);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
        return "partida/listaHora";
    }
    @PostMapping("/reservar")
    public String agregar(
        Model model, 
        Reserva reserva,
        @CookieValue(value = "id", defaultValue = "0") String id_cookie,
        @CookieValue(value = "rol", defaultValue = "0") String rol
        ){
        Usuario user = usuarioService.getById(2);
        //System.out.println(user.toString());
        reserva.setUsuario(user);
        reserva.setTipo(1);
        reserva.getJuagadores().add(user);
        Reserva r = reservaService.saveReserva(reserva);

        Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id_cookie));
		model.addAttribute("id", id_cookie);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
        return "redirect:/partida/lista";
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
        model.addAttribute("partida", new Reserva());
        model.addAttribute("horarioReserva", getHorarioResevas(reservas, canchas));
        model.addAttribute("reservaActive", "active");
        model.addAttribute("fecha", fecha);

        Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id_cookie));
		model.addAttribute("id", id_cookie);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
        
        return "partida/listaHora";
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

}

package com.example.demo.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.example.demo.services.ReservaServiceImplementation;
import com.example.demo.services.UsuarioServiceImplementation;
import com.example.demo.view.pdf.PDFGenerator;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/reporte")
@Controller
public class ReporteController {
	
	
	@Autowired
	UsuarioServiceImplementation usuarioService;
	@Autowired
	ReservaServiceImplementation reservaService;
	
	@GetMapping("/pdf/usuario")
	public void generatePdfUsuario(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		
		List<Usuario> usuarioList = usuarioService.listAll();
		
		PDFGenerator generator = new PDFGenerator();
		generator.setusuarioList(usuarioList);
		generator.generateUser(response);
		
	}
	
	@GetMapping("/usuario")
	public String reporteUsuario(@CookieValue(value = "id", defaultValue="0") String id,@CookieValue(value = "rol", defaultValue="0") String rol,Model model) {
		model.addAttribute("usuarios",usuarioService.listAll());
		Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id));
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
		return "reporte/reporteUsuario";
	}
	
	@GetMapping("/pdf/reserva")
	public void generatePdfReserva(HttpServletResponse response) throws DocumentException, IOException {
		
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY/MM/DD");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		
		
		Calendar c = Calendar.getInstance();
        String startDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH) + 1)+"/"+c.get(Calendar.DATE);
        
		List<Reserva> reservaList = reservaService.listByDate(startDate);
		
		PDFGenerator generator = new PDFGenerator();
		generator.setreservaList(reservaList,startDate);
		generator.generateReserva(response);
		
	}
	
	
	@GetMapping("/reserva")
	public String reporteReserva(@CookieValue(value = "id", defaultValue="0") String id,@CookieValue(value = "rol", defaultValue="0") String rol,Model model) {
		Calendar c = Calendar.getInstance();
        String startDate = c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH) + 1)+"/"+c.get(Calendar.DATE);
        
		List<Reserva> reservaList = reservaService.listByDate(startDate);
		model.addAttribute("reservas",reservaList);
		Usuario usuario = new Usuario();
		usuario = usuarioService.getById(Integer.parseInt(id));
		model.addAttribute("id", id);
		model.addAttribute("rol", rol);
		model.addAttribute("usuario", usuario);
		return "reporte/reporteReserva";
	}
	
}

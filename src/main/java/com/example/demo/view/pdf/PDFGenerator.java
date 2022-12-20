package com.example.demo.view.pdf;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.demo.model.Reserva;
import com.example.demo.model.Usuario;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class PDFGenerator {

	
	// Lista de Usuarios
	private List<Usuario> usuarioList;
	private List<Reserva> reservaList;
	private String fecha;
	
	
	public void generateUser(HttpServletResponse response) throws DocumentException, IOException{
		
		// Creating the Object of Document
		Document document = new Document(PageSize.A4);
		
		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());
		
		// Opening the created document to modify it
		document.open();
		
		//Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		
		// Creating paragraph
		Paragraph paragraph = new Paragraph("Reporte de Usuarios", fontTiltle);
		
		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(paragraph);
		
		// Creating a table of 3 columns
		PdfPTable table = new PdfPTable(4);
		
		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 3, 3, 3, 3 });
		table.setSpacingBefore(5);
		
		// Create Table Cells for table header
		PdfPCell cell = new PdfPCell();
		
		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.BLUE);
		cell.setPadding(5);
		
		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		// Adding headings in the created table cell/ header
		// Adding Cell to table
		cell.setPhrase(new Phrase("Rut", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Nombre", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Apellido", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Reservas", font));
		table.addCell(cell);
		
		// Iterating over the list of students
		for (Usuario usuario : usuarioList) {
					// Adding student id
					table.addCell(usuario.getRut());
					// Adding student name
					table.addCell(usuario.getNombre());
					// Adding student section
					table.addCell(usuario.getApellido());
					table.addCell(String.valueOf(usuario.getReservas().size()));
		}
		// Adding the created table to document
		document.add(table);
		// Closing the document
		document.close();
	}


	public void setusuarioList(List<Usuario> usuarioList2) {
		this.usuarioList=usuarioList2;
		
	}
	public void setreservaList(List<Reserva> reservaList,String fecha) {
		this.reservaList=reservaList;
		this.fecha=fecha;
		
	}
	
	public void generateReserva(HttpServletResponse response) throws DocumentException, IOException{
		
		// Creating the Object of Document
		Document document = new Document(PageSize.A4);
		
		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());
		
		// Opening the created document to modify it
		document.open();
		
		//Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		
		// Creating paragraph
		Paragraph paragraph = new Paragraph("Reporte de Reservas "+fecha, fontTiltle);
		
		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(paragraph);
		
		// Creating a table of 3 columns
		PdfPTable table = new PdfPTable(3);
		
		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 3, 3, 3});
		table.setSpacingBefore(5);
		
		// Create Table Cells for table header
		PdfPCell cell = new PdfPCell();
		
		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.BLUE);
		cell.setPadding(5);
		
		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		// Adding headings in the created table cell/ header
		// Adding Cell to table
		cell.setPhrase(new Phrase("Cancha", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Fecha", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Hora Inicio", font));
		table.addCell(cell);
		
		// Iterating over the list of students
		for (Reserva reserva : reservaList) {
					// Adding student id
					table.addCell(reserva.getCancha().getNombre());
					// Adding student name
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String fecha = dateFormat.format(reserva.getFecha());
					table.addCell(fecha);
					// Adding student section
					table.addCell(reserva.getHoraInicio());
		}
		// Adding the created table to document
		document.add(table);
		// Closing the document
		document.close();
	}
}

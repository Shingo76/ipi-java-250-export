package com.example.demo.service.export;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ExportPDFListeClientsService {

	@Autowired
	ClientRepository clientRepository;

	public void export(ServletOutputStream outputStream) throws DocumentException {

		Document document = new Document();
		PdfWriter.getInstance(document, outputStream);

		document.open();

		List<Client> listeClients = clientRepository.findAll();

	
		PdfPCell cell;
		PdfPTable table = new PdfPTable(5);
		cell = new PdfPCell(new Phrase("Liste des clients"));
	    cell.setColspan(5);
	    table.addCell(cell);
		
		table.addCell("Civilité : ");
		table.addCell("Nom : ");
		table.addCell("Prénom : ");
		table.addCell("Age");
		table.addCell("Adresse : ");

		String sexe;
		for (Client client : listeClients) {
			
			if (client.getSexe().equals("H")) {
				sexe = " - Mr ";
			} else {
				sexe = " - Mme ";
			}

			table.addCell(sexe);
			table.addCell(client.getPrenom());
			table.addCell(client.getNom());
			table.addCell(client.getAge().toString());
			table.addCell(client.getAdresse());

			

		}
		document.add(table);
		document.close();

	}

}

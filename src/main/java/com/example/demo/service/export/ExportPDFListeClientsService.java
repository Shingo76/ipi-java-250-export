package com.example.demo.service.export;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
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
		
		document.add(new Paragraph("Liste des clients : "));
		
		for(Client client : listeClients) {
			String sexe = "";
			if(client.getSexe().equals("M")) {
				sexe = " - Mr ";
			} else {
				sexe = " - Mme ";
			}
			
			document.add(new Paragraph(sexe + client.getNom()
			+ " "  + client.getPrenom() + "  Age : " + client.getAge().toString() + "  et réside au " + client.getAdresse()));
			
		}
		
		
		document.close();
		
	}
	
}

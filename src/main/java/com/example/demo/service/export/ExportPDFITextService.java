package com.example.demo.service.export;

import java.io.OutputStream;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.FactureRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ExportPDFITextService {

	@Autowired 
	FactureRepository factureRepository;

	
	public void export(OutputStream outputStream, Long id) throws DocumentException  {
		
		Document document = new Document();
		PdfWriter.getInstance(document, outputStream);
		
		document.open();
		
		Set<LigneFacture> ligneFacture = factureRepository.getOne(id).getLigneFactures();
		
		 String texte = "Facture de ";
		
		if(factureRepository.getOne(id).getClient().getSexe().equals("H")){
			 texte = texte + "Mr. ";
		}else {
			 texte = texte + "Mme. ";
		}	
		
		document.add(new Paragraph(texte + factureRepository.getOne(id).getClient().getNom() 
				+ " " 
				+ factureRepository.getOne(id).getClient().getPrenom()));
		document.add(new Paragraph(" ---------- "));		
		
		Double prixTotal = 0d;
		
		for (LigneFacture ligne : ligneFacture) {
			Double prixTotalParArticle = deuxChiffresSignificatifs(ligne.getQuantite()*ligne.getArticle().getPrix());

			
			document.add(new Paragraph("Achat : " + ligne.getArticle().getLibelle()
					+ ".     Nombre : " + ligne.getQuantite()
					+ ".     Prix à l'unité : " + ligne.getArticle().getPrix() + " Euros"));
			document.add(new Paragraph("Prix total par type d'article : " 
					+ prixTotalParArticle + " Euros."));
	
			document.add(new Paragraph(" "));

			prixTotal = deuxChiffresSignificatifs(prixTotal + prixTotalParArticle);
			document.add(new Paragraph(" "));

		}
		
		document.add(new Paragraph(" "));
		document.add(new Paragraph("Total : " 
		+ prixTotal + " Euros. "));		
		document.close();
		
	
	}
	
	public static double deuxChiffresSignificatifs(Double montant) {
		
		return (double) (Math.round(montant*100))/100;
		
	}
	
}

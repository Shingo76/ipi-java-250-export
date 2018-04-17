package com.example.demo.service.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Facture;
import com.example.demo.entity.LigneFacture;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.FactureRepository;

@Service
public class ExportXLXSFacturesClientService {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	FactureRepository factureRepository;

	public void export(OutputStream OutputStream, Long idClient) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFFont defaultFont = workbook.createFont();
		defaultFont.setBold(true);
		defaultFont.setItalic(true);
		
		
		List<Facture> listeFactures = factureRepository.findAll();
		List<Facture> listeFacturesClient = new ArrayList<Facture>();

		for (Facture facture : listeFactures) {

			if (facture.getClient().getId().equals(idClient)) {
				listeFacturesClient.add(facture);
			}

		}
		int i = 1;
		for (Facture factureClient : listeFacturesClient) {

			Sheet sheet = workbook.createSheet("Facture" + i);
			int colNum = 0;
			Row entete = sheet.createRow(0);

			Cell celluleEntete = entete.createCell(colNum++);
			celluleEntete.getCellStyle().setFont(defaultFont);
			celluleEntete.setCellValue("Article");

			Cell celluleEntete2 = entete.createCell(colNum++);
			celluleEntete2.setCellValue("Prix unitaire");

			Cell celluleEntete3 = entete.createCell(colNum++);
			celluleEntete3.setCellValue("Nombre");

			colNum++;

			Cell celluleEntete5 = entete.createCell(colNum++);
			celluleEntete5.setCellValue("Cout total par article");

			Set<LigneFacture> listeLigneFactures = factureClient.getLigneFactures();

			Double prixTotal = 0d;

			int numLigne = 2;
			for (LigneFacture ligneFacture : listeLigneFactures) {
				colNum = 0;

				Row ligne = sheet.createRow(numLigne++);

				Cell celluleLibelleArticle = ligne.createCell(colNum++);
				celluleLibelleArticle.setCellValue(ligneFacture.getArticle().getLibelle());

				Cell cellulePrixUnitaire = ligne.createCell(colNum++);
				cellulePrixUnitaire.setCellValue(ligneFacture.getArticle().getPrix() + " €");

				Cell celluleQuantite = ligne.createCell(colNum++);
				celluleQuantite.setCellValue(ligneFacture.getQuantite());

				colNum++;

				Double prixParNombreArticles = ligneFacture.getQuantite() * ligneFacture.getArticle().getPrix();

				Cell cellulePrixTotalParArticles = ligne.createCell(colNum++);
				cellulePrixTotalParArticles
						.setCellValue(ExportPDFITextService.deuxChiffresSignificatifs(prixParNombreArticles) + " €");

				prixTotal = prixTotal + prixParNombreArticles;

				if (colNum == 5) {
					colNum = 0;
				}

			}
			numLigne++;
			numLigne++;

			Row ligneTotal = sheet.createRow(numLigne);

			Cell celluleLibelleTotal = ligneTotal.createCell(0);
			celluleLibelleTotal.setCellValue("Total : ");

			Cell celluleValeurTotal = ligneTotal.createCell(1);
			celluleValeurTotal.setCellValue(prixTotal + " €");

			for (int j = 0; j < 6; j++) {

				sheet.autoSizeColumn(j);

			}

			i++;
		}

		workbook.write(OutputStream);

		workbook.close();
	}
		
}

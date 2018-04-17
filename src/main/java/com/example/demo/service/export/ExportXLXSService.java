package com.example.demo.service.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;

@Service
public class ExportXLXSService {

	@Autowired
	ClientRepository clientRepository;
	
	public void export(OutputStream OutputStream, List<ClientDTO> clients) throws IOException {
		
		List<Client> listeClients = clientRepository.findAll();
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("clients");
		
		Row entete = sheet.createRow(0);
		Cell celluleEntete = entete.createCell(1);
		celluleEntete.setCellValue("Prénoms");
		Cell cellule2Entete = entete.createCell(2);
		cellule2Entete.setCellValue("Noms");
		Cell celluleAge = entete.createCell(3);
		celluleAge.setCellValue("Age");
		Cell celluleAdresse = entete.createCell(4);
		celluleAdresse.setCellValue("Adresse");	
				
		
		for(int i=0; i<listeClients.size(); i++) {
			
			
			Row ligne1 = sheet.createRow(i+2);
			Cell celluleAutoIncre = ligne1.createCell(0);
			celluleAutoIncre.setCellValue(i+1);
			Cell cellule = ligne1.createCell(1);
			cellule.setCellValue(listeClients.get(i).getPrenom());
			Cell cellule2 = ligne1.createCell(2);
			cellule2.setCellValue(listeClients.get(i).getNom());
			Cell cellule3 = ligne1.createCell(3);
			cellule3.setCellValue(listeClients.get(i).getAge());
			Cell cellule4 = ligne1.createCell(4);
			cellule4.setCellValue(listeClients.get(i).getAdresse());
		}
		
		for (int j = 0; j < 5; j++) {

			sheet.autoSizeColumn(j);

		}
		
		
		workbook.write(OutputStream);
		workbook.close();
	}
	
}

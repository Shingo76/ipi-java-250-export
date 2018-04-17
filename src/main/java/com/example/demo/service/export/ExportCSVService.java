package com.example.demo.service.export;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import com.example.demo.repository.ClientRepository;



@Service
public class ExportCSVService {

	@Autowired
	ClientRepository clientRepository;
	
	public void export(PrintWriter writer, List<ClientDTO> clients) {
		
		List<Client> listeClients = clientRepository.findAll();
		
		for(int i=0; i<listeClients.size(); i++) {
		
		writer.print("Client N°" + (i+1));
		writer.println();
		writer.print(replace(listeClients.get(i).getPrenom())
				+";"+replace(listeClients.get(i).getNom()));
		writer.println();
		writer.println();
		
		}
	}

	private String replace(String valeur) {
		valeur = valeur.replace("\"", "\"\"");
		if(valeur.contains(";")) {
			valeur = "\"" + valeur + "\"";
		}
		
		return valeur;
		
	}
	
}

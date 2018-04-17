package com.example.demo.controller;

import com.example.demo.dto.ClientDTO;
import com.example.demo.service.ClientService;
import com.example.demo.service.export.ExportCSVService;
import com.example.demo.service.export.ExportPDFITextService;
import com.example.demo.service.export.ExportPDFListeClientsService;
import com.example.demo.service.export.ExportXLXSFacturesClientService;
import com.example.demo.service.export.ExportXLXSService;
import com.itextpdf.text.DocumentException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class ExportController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ExportCSVService exportCSVService;
    
    @Autowired
    private ExportXLXSService exportXLXSService;
    
    @Autowired
    ExportXLXSFacturesClientService exportXLXSFacturesClientService;

    @Autowired
    private ExportPDFITextService exportPDFITextService;

    @Autowired
    private ExportPDFListeClientsService exportPDFListeClientsService;
    
    @GetMapping("/clients/csv")
    public void clientsCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.csv\"");
        List<ClientDTO> clients = clientService.findAllClients();
        exportCSVService.export(response.getWriter(), clients);
    }
    
    @GetMapping("/clients/xlxs")
    public void clientsXLXS(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xlxs");
        response.setHeader("Content-Disposition", "attachment; filename=\"clients.xlxs\"");
        List<ClientDTO> clients = clientService.findAllClients();
        exportXLXSService.export(response.getOutputStream(), clients);
    }
    
    
    @GetMapping("/facturesClient/{id}/xlxs")
    public void facturesClientXLXS(@PathVariable("id") Long id,HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xlxs");
        response.setHeader("Content-Disposition", "attachment; filename=\"facturesClient.xlxs\"");
        exportXLXSFacturesClientService.export(response.getOutputStream(), id);
    }
    
    
    @GetMapping("/listeClientsPDF/PDF")
    public void listeClientPDF(HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"listeClients.pdf\"");
        exportPDFListeClientsService.export(response.getOutputStream());
    }
  

    @GetMapping("/factures/{id}/pdf")
    public void facturePDF(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"factures " + id + ".pdf\"");   
        exportPDFITextService.export(response.getOutputStream(), id);
    }

}

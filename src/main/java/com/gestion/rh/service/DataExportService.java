package com.gestion.rh.service;

import com.gestion.rh.models.Collaborateur;
import com.gestion.rh.repository.CollaborateurRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataExportService {

    private final CollaborateurRepository collaborateurRepository;

    public void exportDataToExcel(HttpServletResponse response) throws IOException {
        try (OutputStream outputStream = response.getOutputStream()) {

            // Set the file name and content type
            String filename = "collaborateurs.xlsx";
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

            // Retrieve the collaborateurs data from the database
            List<Collaborateur> collaborateurs = collaborateurRepository.findAll();

            // Create the workbook and sheet
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Collaborateurs");

            // Create the header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Prénom");
            headerRow.createCell(2).setCellValue("Nom");
            headerRow.createCell(3).setCellValue("Email");
            headerRow.createCell(4).setCellValue("Matricule");
            headerRow.createCell(5).setCellValue("Abbréviation");
            headerRow.createCell(6).setCellValue("Ancien Manager RH");
            headerRow.createCell(7).setCellValue("Manager RH");
            headerRow.createCell(8).setCellValue("Sexe");
            headerRow.createCell(9).setCellValue("Site");
            headerRow.createCell(10).setCellValue("BU");
            headerRow.createCell(11).setCellValue("Mois BAP");
            headerRow.createCell(12).setCellValue("Date de départ");
            headerRow.createCell(13).setCellValue("Ancien Collaborateur");
            headerRow.createCell(14).setCellValue("Intégration semaine");
            headerRow.createCell(15).setCellValue("Date de participation");
            headerRow.createCell(16).setCellValue("Poste App");
            headerRow.createCell(17).setCellValue("Poste Actuel");
            headerRow.createCell(18).setCellValue("Salaire");
            headerRow.createCell(19).setCellValue("Diplôme");

            // Create the data rows
            int rowNum = 1;
            for (Collaborateur collaborateur : collaborateurs) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(collaborateur.getId());
                row.createCell(1).setCellValue(collaborateur.getPrenom());
                row.createCell(2).setCellValue(collaborateur.getNom());
                row.createCell(3).setCellValue(collaborateur.getEmail());
                row.createCell(4).setCellValue(collaborateur.getMatricule());
                row.createCell(5).setCellValue(collaborateur.getAbreviation());
                row.createCell(6).setCellValue(collaborateur.getAncien_manager_rh());
                row.createCell(7).setCellValue(collaborateur.getManager_rh());
                row.createCell(8).setCellValue(collaborateur.getSexe());
                row.createCell(9).setCellValue(collaborateur.getSite());
                row.createCell(10).setCellValue(collaborateur.getBU());
                row.createCell(11).setCellValue(collaborateur.getMois_bap());
                row.createCell(12).setCellValue(collaborateur.getDate_Dpart() != null ? collaborateur.getDate_Dpart().toString() : "");
                row.createCell(13).setCellValue(collaborateur.isAncien_Collaborateur());
                row.createCell(14).setCellValue(collaborateur.isIntegration_semaine());
            }

            workbook.write(outputStream);
            // Close the workbook and output stream
            workbook.close();
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error exporting data to Excel file: " + e.getMessage());
        }
    }

}


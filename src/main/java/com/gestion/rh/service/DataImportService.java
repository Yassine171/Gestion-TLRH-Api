package com.gestion.rh.service;

import com.gestion.rh.models.Collaborateur;
import com.gestion.rh.repository.CollaborateurRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataImportService {

    private final CollaborateurRepository collaborateurRepository;

    public void importData(MultipartFile file) throws IOException {
        // Read the data from the file
        Workbook workbook = getWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        // Process the data and save it to the database
        List<Collaborateur> collaborateurs = new ArrayList<>();
        for (Row row : sheet) {
            // Skip the header row
            if (row.getRowNum() == 0) {
                continue;
            }

            // Read the data from the row
            String prenom = row.getCell(0).getStringCellValue();
            String nom = row.getCell(1).getStringCellValue();
            String email = row.getCell(2).getStringCellValue();
            String matricule = row.getCell(3).getStringCellValue();
            String abreviation = row.getCell(4).getStringCellValue();
            String ancienManagerRh = row.getCell(5).getStringCellValue();
            String managerRh = row.getCell(6).getStringCellValue();
            String sexe = row.getCell(7).getStringCellValue();
            String site = row.getCell(8).getStringCellValue();
            String bu = row.getCell(9).getStringCellValue();
            String moisBap = row.getCell(10).getStringCellValue();
            LocalDate dateDpart = row.getCell(11).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            boolean ancienCollaborateur = row.getCell(12).getBooleanCellValue();
            boolean integrationSemaine = row.getCell(13).getBooleanCellValue();
            LocalDate dateParticipation = row.getCell(14).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String posteApp = row.getCell(15).getStringCellValue();
            String posteActuel = row.getCell(16).getStringCellValue();
            String salaire = row.getCell(17).getStringCellValue();

            // Create the collaborateur object
            Collaborateur collaborateur = Collaborateur.builder()
                    .prenom(prenom)
                    .nom(nom)
                    .email(email)
                    .matricule(matricule)
                    .abreviation(abreviation)
                    .ancien_manager_rh(ancienManagerRh)
                    .manager_rh(managerRh)
                    .sexe(sexe)
                    .site(site)
                    .BU(bu)
                    .mois_bap(moisBap)
                    .Date_Dpart(dateDpart)
                    .ancien_Collaborateur(ancienCollaborateur)
                    .integration_semaine(integrationSemaine)
                    .Date_Participation(dateParticipation)
                    .Poste_App(posteApp)
                    .Poste_Actuel(posteActuel)
                    .salaire(salaire)
                    .build();

            collaborateurs.add(collaborateur);
        }

        collaborateurRepository.saveAll(collaborateurs);
    }

    private Workbook getWorkbook(MultipartFile file) throws IOException {
        Workbook workbook;
        if (FilenameUtils.getExtension(file.getOriginalFilename()).equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (FilenameUtils.getExtension(file.getOriginalFilename()).equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new IOException("Invalid file type. Only Excel files are supported.");
        }
        return workbook;
    }
}


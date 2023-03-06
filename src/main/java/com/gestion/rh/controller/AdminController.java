package com.gestion.rh.controller;

import com.gestion.rh.service.DataExportService;
import com.gestion.rh.service.DataImportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private DataImportService dataImportService;
    @Autowired
    private DataExportService dataExportService;

    @PostMapping("/import")
    public ResponseEntity<String> importData(@RequestParam("file") MultipartFile file) {
        try {
            dataImportService.importData(file);
            return ResponseEntity.ok("Data imported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing data");
        }
    }

    @GetMapping("/export")
    public void exportCollaborateursToExcel(HttpServletResponse response) throws IOException {
        dataExportService.exportDataToExcel(response);
    }
}
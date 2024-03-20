package com.bit.csv_parser.controller;

import com.bit.csv_parser.service.ParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequiredArgsConstructor

public class ParserController {

    final ParserService service;
    final static String FILENAME = "fetched_players.csv";

    @PostMapping("/players")
    public ResponseEntity<Resource> uploadCSV(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty() || !service.uploadCSV(file)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        InputStreamResource newFile = new InputStreamResource(service.parseCSV());
        return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + FILENAME)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(newFile);
    }

    @GetMapping ("/player")
    public ResponseEntity<Resource> downloadCSV() {
        InputStreamResource file = new InputStreamResource(service.parseCSV());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + FILENAME)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}

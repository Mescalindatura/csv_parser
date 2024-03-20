package com.bit.csv_parser.controller;

import com.bit.csv_parser.service.ParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

@RestController
@RequiredArgsConstructor

public class ParserController {

    final ParserService service;

//    @PostMapping("/upload")
//    public ResponseEntity<bytes[]> uploadCSV(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
//        }
//        ByteArrayOutputStream out = service.save(file);
//    }

}

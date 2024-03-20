package com.bit.csv_parser.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUploader {

    public final static String PATH = "./players.csv";
    public static String TYPE = "text/csv";


    public static void save(MultipartFile file) throws IOException {

            byte[] bytes = file.getBytes();
            Files.write(Path.of(PATH), bytes);

    }

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }
}

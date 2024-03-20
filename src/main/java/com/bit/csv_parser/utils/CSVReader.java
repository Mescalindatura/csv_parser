package com.bit.csv_parser.utils;

import com.bit.csv_parser.dto.PlayerFromFile;
import com.bit.csv_parser.exception.DataFormatIsNotCorrect;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class CSVReader {



    public static List<PlayerFromFile> extractPlayers (String filePath) {
        List<PlayerFromFile> players = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].matches("\\d+")) {
                    PlayerFromFile player = new PlayerFromFile(values[0], values[1]);
                    players.add(player);
                }
            }
        } catch (Exception e) {
            throw new DataFormatIsNotCorrect(e.getMessage());
        }
        return players;
    }


}

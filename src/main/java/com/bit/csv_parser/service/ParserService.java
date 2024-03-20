package com.bit.csv_parser.service;

import com.bit.csv_parser.model.Player;
import com.bit.csv_parser.repo.PlayersRepo;
import com.bit.csv_parser.utils.CSVReader;
import com.bit.csv_parser.utils.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParserService {

    final PlayersRepo repo;

//post: fieuploader=>csvreader=>dataprovider=>repo=>load()
    //get: csvreader=>dataprovider=>repo=>load()
    //update: =>repo=>dataprovider=>repo=>socketmessage
//            List<PlayerFromFile> players = CSVReader.extractPlayers(PATH);
    public ByteArrayInputStream load() {
        List<Player> players = repo.findAll();

        ByteArrayInputStream in = CSVWriter.playersToCSV(players);
        return in;
    }
}

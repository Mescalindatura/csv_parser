package com.bit.csv_parser.service;

import com.bit.csv_parser.dto.PlayerData;
import com.bit.csv_parser.dto.PlayerFromFile;
import com.bit.csv_parser.model.Player;
import com.bit.csv_parser.repo.PlayersRepo;
import com.bit.csv_parser.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.bit.csv_parser.utils.FileUploader.PATH;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParserService {

    final PlayersRepo repo;

    //post: fileuploader=>csvreader=>dataprovider=>repo=>load()
    //get: csvreader=>dataprovider=>repo=>load()
    //update: =>repo=>dataprovider=>repo=>socketmessage
//            
    public ByteArrayInputStream downloadCSV() {
        List<Player> players = repo.findAll();

        ByteArrayInputStream in = CSVWriter.playersToCSV(players);
        return in;
    }

    private boolean uploadCSV() {
        return false;
    }

    public ByteArrayInputStream parseCSV() {
        List<PlayerFromFile> players = CSVReader.extractPlayers(PATH);
//todo: delete playerfromfile, extract id only

        List<PlayerData> updatedPlayers = updateData(players);

        List<Player> convertedPlayers = convertPlayers(updatedPlayers);

        return CSVWriter.playersToCSV(convertedPlayers);
    }

    private List<Player> convertPlayers(List<PlayerData> updatedPlayers) {
        List<Player> convertedPlayers = new ArrayList<>();
        updatedPlayers.forEach(p -> {
            convertedPlayers.add(PlayerConverter.convertDTOtoModel(p));
        });
        return convertedPlayers;
    }


    private List<PlayerData> updateData(List<PlayerFromFile> players) {
        List<PlayerData> updatedPlayers = new ArrayList<>();
        players.forEach(p -> {
            try {
                updatedPlayers.add(DataProvider.getPlayersByAPI(p));
            } catch (URISyntaxException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
        return updatedPlayers;
    }
}

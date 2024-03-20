package com.bit.csv_parser.service;

import com.bit.csv_parser.dto.PlayerData;
import com.bit.csv_parser.dto.PlayerFromFile;
import com.bit.csv_parser.model.Player;
import com.bit.csv_parser.repo.PlayersRepo;
import com.bit.csv_parser.utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.bit.csv_parser.utils.FileUploader.PATH;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParserService {

    final PlayersRepo repo;
    private final PlayerWebSocketHandler webSocketHandler;

    public ByteArrayInputStream buildFileFromRepo() {
        List<Player> players = repo.findAll();
        ByteArrayInputStream in = CSVWriter.playersToCSV(players);
        return in;
    }

    public boolean uploadCSV(MultipartFile file) throws IOException {
        if (!FileUploader.hasCSVFormat(file))
            return false;
        FileUploader.save(file);
        return true;
    }

    public ByteArrayInputStream parseCSV() {
        if (repo.count() > 0)
            return buildFileFromRepo();
        List<PlayerFromFile> players = CSVReader.extractPlayers(PATH);
        List<PlayerData> updatedPlayers = updateData(players);
        List<Player> convertedPlayers = convertPlayers(updatedPlayers);
        return CSVWriter.playersToCSV(convertedPlayers);
    }

    private List<Player> convertPlayers(List<PlayerData> updatedPlayers) {
        List<Player> convertedPlayers = new ArrayList<>();
        updatedPlayers.forEach(p -> {
            Player player = PlayerConverter.convertDataToModel(p);
            convertedPlayers.add(player);
            repo.save(player);
        });
        return convertedPlayers;
    }


    private List<PlayerData> updateData(List<PlayerFromFile> players) {
        List<PlayerData> updatedPlayers = new ArrayList<>();
        players.forEach(p -> {
            updatedPlayers.add(DataProvider.getPlayersByAPI(p));
        });
        return updatedPlayers;
    }

    @Scheduled(fixedRate = 15 * 60 * 1000)
    public void updatePlayerDetails() {
        List<Player> players = repo.findAll();
        players.forEach(p -> {
            PlayerData pd = DataProvider.getPlayersByAPI(PlayerConverter.convertModelToFile(p));
            Player fetchedPlayer = PlayerConverter.convertDataToModel(pd);
            if (!p.equals(fetchedPlayer)) {
                repo.save(fetchedPlayer);
                webSocketHandler.sendMessage("Player data changed: "+ fetchedPlayer.toString());
            }        });
    }

}

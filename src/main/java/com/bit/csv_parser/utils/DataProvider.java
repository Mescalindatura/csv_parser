package com.bit.csv_parser.utils;

import com.bit.csv_parser.dto.PlayerData;
import com.bit.csv_parser.dto.PlayerFromFile;
import com.bit.csv_parser.dto.PlayersDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class DataProvider {

    private static final String API_KEY = "0c61b6ef-9fe9-4b4b-9677-d631c6810868";

    public static PlayerData getPlayersByAPI(PlayerFromFile player) throws URISyntaxException, JsonProcessingException {
        String id = player.id();
        String base_url = "https://api.balldontlie.io/v1/players/";
        RestTemplate rest = new RestTemplate();
        RequestEntity<Void> req = RequestEntity.get(new URI(base_url+id)).header("Authorization", API_KEY).build();
        ResponseEntity<String> response = rest.exchange(req, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        PlayersDTO playerData = objectMapper.readValue(response.getBody(), PlayersDTO.class);
        return playerData.data();
    }
}

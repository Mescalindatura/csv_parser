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
    private static final String BASE_URL = "https://api.balldontlie.io/v1/players/";


    public static PlayerData getPlayersByAPI(PlayerFromFile player) {
        String id = player.id();
        RestTemplate rest = new RestTemplate();
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(BASE_URL + id)).header("Authorization", API_KEY).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<String> response = rest.exchange(req, String.class);
        if (response.getStatusCode().is4xxClientError()) {

            return new PlayerData(Integer.parseInt(id), "Player not found");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PlayersDTO fetchedData = objectMapper.readValue(response.getBody(), PlayersDTO.class);
            return fetchedData.data();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

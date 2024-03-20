package com.bit.csv_parser;


import com.bit.csv_parser.dto.PlayerData;
import com.bit.csv_parser.dto.PlayerFromFile;
import com.bit.csv_parser.dto.PlayersDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//        try {
//            getPlayersByAPI(players.get(0));
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }


        // api key 0c61b6ef-9fe9-4b4b-9677-d631c6810868 => put it to constants or env values
        // https://docs.balldontlie.io/#get-a-specific-player
    }





}

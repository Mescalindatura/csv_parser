package com.bit.csv_parser.utils;

import com.bit.csv_parser.dto.PlayerData;
import com.bit.csv_parser.dto.PlayerFromFile;
import com.bit.csv_parser.model.Player;

public class PlayerConverter {
    public static Player convertDataToModel(PlayerData p) {
        Player player = new Player(p.getId(), p.getFirst_name(), p.getLast_name(), p.getPosition(), p.getHeight(), p.getWeight(), p.getJersey_number(), p.getCollege(), p.getCountry(), p.getDraft_year(), p.getDraft_round(), p.getDraft_number(), p.getTeam().getFull_name(), p.getTeam().getDivision(), p.getTeam().getConference());
        return player;
    }

    public static PlayerFromFile convertModelToFile(Player p) {
        String id = String.valueOf(p.getId());
        return new PlayerFromFile(id, p.getFirst_name());
    }
}

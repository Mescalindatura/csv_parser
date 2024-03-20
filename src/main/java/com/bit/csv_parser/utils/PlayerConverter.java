package com.bit.csv_parser.utils;

import com.bit.csv_parser.dto.PlayerData;
import com.bit.csv_parser.model.Player;

public class PlayerConverter {
    public static Player convertDTOtoModel (PlayerData p) {
      String fullName = p.getFirst_name()+p.getLast_name();
        Player player = new Player(p.getId(), fullName, p.getPosition(), p.getHeight(), p.getWeight(), p.getJersey_number(), p.getCollege(), p.getCountry(), p.getDraft_year(), p.getDraft_round(), p.getDraft_number(), p.getTeam().getFull_name(), p.getTeam().getDivision(), p.getTeam().getConference());
        return player;
    }
}

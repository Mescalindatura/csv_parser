package com.bit.csv_parser.dto;


import lombok.*;

@Data
public class PlayerData {
    private int id;
    private String first_name;
    private String last_name;
    private String position;
    private String height;
    private String weight;
    private String jersey_number;
    private String college;
    private String country;
    private int draft_year;
    private int draft_round;
    private int draft_number;
    private TeamData team;

    public PlayerData(int id, String name) {
        this.id = id;
        this.first_name = name;

    }
}

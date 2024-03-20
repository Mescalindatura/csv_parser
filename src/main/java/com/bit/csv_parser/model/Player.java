package com.bit.csv_parser.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="players")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Player {
    @Id
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
    private String teamFullName;
    private String teamDivision;
    private String teamConference;

    public String getTableRow(){
        StringBuilder sb = new StringBuilder(id +","+ first_name+","+ last_name+","+ position+","+ height+","+ weight+","+ jersey_number+","+ college+","+ country+","+ draft_year+","+ draft_round+","+ draft_number+","+ teamFullName+","+ teamConference+","+ teamDivision);
        return sb.toString();
    }
}

package com.bit.csv_parser.utils;

import com.bit.csv_parser.model.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class CSVWriter {

    static final String TABLE_HEADER = "id,fullName,position,height,weight,jersey_number,college,country,draft_year,draft_round,draft_number,teamFullName,teamConference,teamDivision";
    public static ByteArrayInputStream playersToCSV(List<Player> players) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (OutputStreamWriter writer = new OutputStreamWriter(outputStream)) {

            writer.write(TABLE_HEADER);
            writer.write(System.lineSeparator());

            for (Player player : players) {
                writer.write(player.getTableRow());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}

package com.bit.csv_parser.repo;

import com.bit.csv_parser.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepo extends JpaRepository<Player, Integer> {
}

package com.teammanager.dto;

import lombok.Data;
import java.util.List;

@Data
public class TeamDTO {
    private Long id;
    private String name;
    private String sport;
    private String description;
    private UserDTO coach;
    private List<UserDTO> players;
    private int playerCount;
    private int eventCount;
} 
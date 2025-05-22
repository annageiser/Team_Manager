package ch.fhnw.teammanager.model;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role; // "ADMIN", "COACH", "PLAYER"

    // Getter/Setter
}
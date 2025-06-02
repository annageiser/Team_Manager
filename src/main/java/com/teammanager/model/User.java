package com.teammanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String firstName;
    private String lastName;
    
    @OneToMany(mappedBy = "coach")
    private List<Team> coachedTeams;
    
    @ManyToMany(mappedBy = "players")
    private List<Team> teams;
    
    @OneToMany(mappedBy = "player")
    private List<Participation> participations;
}

enum Role {
    ADMIN, PLAYER, COACH
} 
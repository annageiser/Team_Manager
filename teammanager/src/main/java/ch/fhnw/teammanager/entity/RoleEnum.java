package ch.fhnw.teammanager.entity;

public enum Role {
    ADMIN("Admin"),
    COACH("Coach"), 
    PLAYER("Player");
    
    private final String displayName;
    
    Role(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
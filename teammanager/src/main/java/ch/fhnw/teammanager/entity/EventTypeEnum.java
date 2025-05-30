package ch.fhnw.teammanager.entity;

public enum EventType {
    TRAINING("Training"),
    GAME("Game"),
    MEETING("Meeting"),
    TOURNAMENT("Tournament");
    
    private final String displayName;
    
    EventType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
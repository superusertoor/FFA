package de.toor.ffa.turnier;

public class Tournament {

    private CloudPlayer cloudPlayer;
    private TournamentMode mode;

    public Tournament(CloudPlayer cloudPlayer) {
        this.cloudPlayer = cloudPlayer;
        mode = null;
    }

    public CloudPlayer getHost() {
        return cloudPlayer;
    }

    public TournamentMode getMode() {
        return mode;
    }

    public void start() {
        //announce
    }

    public void setMode(TournamentMode mode) {
        this.mode = mode;
    }
}

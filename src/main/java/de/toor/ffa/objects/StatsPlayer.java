package de.toor.ffa.objects;

import de.scar.cloud.database.Database;
import de.scar.cloud.player.CloudPlayer;
import org.bukkit.entity.Player;
import toor.datasource.DataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StatsPlayer {

    private String color;
    private int kills;
    private int deaths;
    private int killstreak;
    private Player player;
    private int ranking;
    private UUID uuid;
    private String name;
    private int userId;
    private static List<UUID> requests = new ArrayList<>();
    private CloudPlayer cloudPlayer;
    private boolean inDuel;
    private StatsPlayer opponent;

    public StatsPlayer(CloudPlayer cloudPlayer) {
        this.cloudPlayer = cloudPlayer;
        try {
            final PreparedStatement ps = DataSource.getConnection().prepareStatement("SELECT * FROM FFA WHERE userid = ?");
            ps.setInt(1, cloudPlayer.getDatabaseIdentifier());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                color = cloudPlayer.getRank().getColor();
                kills = rs.getInt("kills");
                deaths = rs.getInt("deaths");
                killstreak = 0;
                name = cloudPlayer.getName();
                uuid = Database.getUUID(name);
                userId = cloudPlayer.getDatabaseIdentifier();
                ranking = getRank(cloudPlayer.getDatabaseIdentifier());
                inDuel = false;
            }
            ps.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        StatsPlayerPool.addPlayer(uuid, this);
    }

    public void saveStats() {
        try {
            final PreparedStatement ps = DataSource.getConnection().prepareStatement("UPDATE FFA SET kills = ?, deaths = ? WHERE userid = ?");
            ps.setInt(1, kills);
            ps.setInt(2, deaths);
            ps.setInt(3, userId);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getRank(int userId) {
        try {
            final PreparedStatement ps = DataSource.getConnection().prepareStatement("SELECT * FROM FFA ORDER BY kills DESC");
            final ResultSet rs = ps.executeQuery();
            int i = 0;
            while(rs.next()) {
                i++;
                if(rs.getInt(1) == userId) {
                    break;
                }
            }
            return i;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public void unregister() {
        StatsPlayerPool.pool.remove(player);
    }

    public void resetKillstreak() {
        killstreak = 0;
    }

    public void addDeath() {
        deaths++;
    }

    public void setOpponent(StatsPlayer statsPlayer) {
        this.opponent = opponent;
    }

    public StatsPlayer getOpponent() {
        return opponent;
    }

    public void addKill() {
        kills++;
    }

    public void addKillstreak() {
        killstreak++;
    }

    public String getColor() {
        return color;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getKillstreak() {
        return killstreak;
    }

    public int getRanking() {
        return ranking;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public void addRequest(UUID uuid) {
        requests.add(uuid);
    }

    public List<UUID> getRequests() {
        return requests;
    }

    public CloudPlayer getCloudPlayer() {
        return cloudPlayer;
    }

    public void setInDuell(boolean bool) {
        inDuel = bool;
    }

    public boolean isInDuel() {
        return inDuel;
    }
}
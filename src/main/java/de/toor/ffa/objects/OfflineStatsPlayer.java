package de.toor.ffa.objects;

import de.scar.cloud.player.CloudPlayer;
import de.scar.cloud.player.OfflineCloudPlayer;
import toor.datasource.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfflineStatsPlayer {

    private int kills;
    private int deaths;
    private int ranking;
    private String color;
    private String name;

    public OfflineStatsPlayer(String username) {
        try {
            OfflineCloudPlayer cloudPlayer = new OfflineCloudPlayer(username);
            final PreparedStatement ps = DataSource.getConnection().prepareStatement("SELECT * FROM FFA WHERE userid = ?");
            ps.setInt(1, cloudPlayer.getDatabaseIdentifier());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                color = cloudPlayer.getRank().getColor();
                kills = rs.getInt("KILLS");
                deaths = rs.getInt("DEATHS");
                name = cloudPlayer.getName();
                ranking = getRank(cloudPlayer.getDatabaseIdentifier());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public OfflineStatsPlayer(CloudPlayer cloudPlayer) {
        try {
            final PreparedStatement ps = DataSource.getConnection().prepareStatement("SELECT * FROM FFA WHERE userid = ?");
            ps.setInt(1, cloudPlayer.getDatabaseIdentifier());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                color = cloudPlayer.getRank().getColor();
                kills = rs.getInt("KILLS");
                deaths = rs.getInt("DEATHS");
                name = cloudPlayer.getName();
                ranking = getRank(cloudPlayer.getDatabaseIdentifier());
            }
        }catch(SQLException e){
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

    public int getKills() {
        return kills;
    }

    public String getColor() {
        return color;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRanking() {
        return ranking;
    }

}

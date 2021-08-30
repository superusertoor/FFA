package de.toor.ffa.objects;

import de.scar.cloud.player.CloudPlayer;
import toor.datasource.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopPlayers {

    List<OfflineStatsPlayer> top = new ArrayList<>();

    public TopPlayers() {
        try {
            final PreparedStatement ps = DataSource.getConnection().prepareStatement("SELECT * FROM FFA ORDER BY kills DESC LIMIT 5");
            final ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                while(rs.next()) {
                    top.add(new OfflineStatsPlayer(new CloudPlayer().sync(rs.getInt("userid"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OfflineStatsPlayer> getTop() {
        return top;
    }

}
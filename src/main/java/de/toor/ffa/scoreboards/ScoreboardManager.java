package de.toor.ffa.scoreboards;

import de.scar.cloud.main.Cloud;
import de.scar.cloud.player.CloudPlayer;
import de.scar.cloud.player.Language;
import de.scar.cloud.scoreboard.ObjectiveType;
import de.toor.ffa.objects.StatsPlayer;
import de.toor.ffa.objects.StatsPlayerPool;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public final class ScoreboardManager {

    public static void updateKills(Player player) {
        StatsPlayer statsPlayer = StatsPlayerPool.getStatsPlayer(player.getUniqueId());
        Language language = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId()).getLanguage();
        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(updateTeam(player.getScoreboard(), "Kills", language.getMessage("scoreboard-kills-value").replaceAll("%kills%", String.valueOf(statsPlayer.getKills())), "", ChatColor.GREEN));
    }

    public static void updateKillstreak(Player player) {
        StatsPlayer statsPlayer = StatsPlayerPool.getStatsPlayer(player.getUniqueId());
        Language language = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId()).getLanguage();
        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(updateTeam(player.getScoreboard(), "Killstreak", language.getMessage("scoreboard-killstreak-value").replaceAll("%killstreak%", String.valueOf(statsPlayer.getKillstreak())), "", ChatColor.YELLOW));
    }

    public static void updateDeaths(Player player) {
        StatsPlayer statsPlayer = StatsPlayerPool.getStatsPlayer(player.getUniqueId());
        Language language = Cloud.INSTANCE.getCloudPlayerManager().getCachedCloudPlayer(player.getUniqueId()).getLanguage();
        player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getScore(updateTeam(player.getScoreboard(), "Deaths", language.getMessage("scoreboard-deaths-value").replaceAll("%deaths%", String.valueOf(statsPlayer.getDeaths())), "", ChatColor.RED));
    }

    public static void createScoreboard(CloudPlayer cloudPlayer, StatsPlayer statsPlayer) {
        Language language = cloudPlayer.getLanguage();
        Scoreboard scoreboard = cloudPlayer.getBukkitPlayer().getScoreboard();
        Objective objective = scoreboard.registerNewObjective(ObjectiveType.GAMESCOREBOARD.getName(), "dummy");
        objective.setDisplayName(language.getMessage("scoreboard-header"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        final Objective healthObj = scoreboard.registerNewObjective("HealthDisplay", "Hearts");
        healthObj.setDisplaySlot(DisplaySlot.BELOW_NAME);
        healthObj.setDisplayName(ChatColor.RED+"\u2764");
        for(Player online : Bukkit.getOnlinePlayers()) {
            healthObj.getScore(online.getName()).setScore(10);
        }
        objective.getScore(" ").setScore(12);
        objective.getScore(language.getMessage("scoreboard-map")).setScore(11);
        objective.getScore(language.getMessage("scoreboard-map-value").replace("%motd%", Bukkit.getMotd())).setScore(10);
        objective.getScore("  ").setScore(9);
        objective.getScore(language.getMessage("scoreboard-kills")).setScore(8);
        objective.getScore(updateTeam(scoreboard, "Kills", language.getMessage("scoreboard-kills-value").replaceAll("%kills%", String.valueOf(statsPlayer.getKills())), "", ChatColor.GREEN)).setScore(7);
        objective.getScore("   ").setScore(6);
        objective.getScore(language.getMessage("scoreboard-deaths")).setScore(5);
        objective.getScore(updateTeam(scoreboard, "Deaths", language.getMessage("scoreboard-deaths-value").replaceAll("%deaths%", String.valueOf(statsPlayer.getDeaths())), "", ChatColor.RED)).setScore(4);
        objective.getScore("    ").setScore(3);
        objective.getScore(language.getMessage("scoreboard-killstreak")).setScore(2);
        objective.getScore(updateTeam(scoreboard, "Killstreak", language.getMessage("scoreboard-killstreak-value").replaceAll("%killstreak%", String.valueOf(statsPlayer.getKillstreak())), "", ChatColor.YELLOW)).setScore(1);
    }

    private static String updateTeam(Scoreboard scoreboard, String Team, String prefix, String suffix, ChatColor entry) {
        Team team = scoreboard.getTeam(Team);
        if (team == null) {
            team = scoreboard.registerNewTeam(Team);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());
        return entry.toString();
    }
}


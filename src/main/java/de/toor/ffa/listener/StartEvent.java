package de.toor.ffa.listener;

import de.scar.cloud.events.custom.CloudServerStartEvent;
import de.scar.cloud.manager.LanguageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class StartEvent implements Listener {

    @EventHandler
    public void onCloudServerStart(CloudServerStartEvent event) {
        LanguageManager german = new LanguageManager("Deutsch");
        german.addString("scoreboard-header", "§6§lTHUGLIFE.WTF");
        german.addString("target-stats-header-alltime", "§7- = %color%%targetname%§7's §eStats §7(Gesamt) §7= -");
        german.addString("target-stats-header-30d", "§7- = %color%%targetname%§7's §eStats §7(30 Tage) §7= -");
        german.addString("own-stats-header", "§7- = §eDeine Stats §7= -");
        german.addString("ranking", " §7Ranking: §e%ranking%");
        german.addString("kills", " §7Kills: §e%kills%");
        german.addString("deaths", " §7Tode: §e%deaths%");
        german.addString("killstreak", " §7Killstreak: §e%killstreak%");
        german.addString("kd", " §7K/D: §e%kd%");
        german.addString("kdNA", " §7K/D: §eN/A");
        german.addString("killstreak-announcement", "§7[§cFFA§7] %color%%targetname% hat eine Killstreak von %amount%");
        german.addString("you-died", "§7[§cFFA§7] §cDu bist gestorben");
        german.addString("you-killed", "§7[§cFFA§7] §cDu hast %color%%targetname% §cgetötet");
        german.addString("you-were-killed", "§7[§cFFA§7] §cDu wurdest von %color%%targetname% §cgetötet");
        german.addString("vanish-enabled", "§7[§cFFA§7] §5Vanish: §aAktiviert");
        german.addString("vanish-disabled", "§7[§cFFA§7] §5Vanish: §cDeaktiviert");
        german.addString("vanish-usage", "§7[§cFFA§7] §cBenutze: /Vanish");
        german.addString("no-perm", "§7[§cFFA§7] §cDazu hast du keine Berechtigung");
        german.addString("spectate-self", "§7[§cFFA§7] §cDu kannst dich nich selbst spectaten");
        german.addString("spectate-usage", "§7[§cFFA§7] §cBenutze: /Spectate [Name]");
        german.addString("spectate-disabled", "§7[§cFFA§7] §5Vanish: §cDeaktiviert");
        german.addString("spectate-enabled", "§7[§cFFA§7] §5Vanish: §aAktiviert");
        german.addString("already-spectating", "§7[§cFFA§7] §cDu bist bereits im Spectatormode");
        german.addString("target-not-found", "§7[§cFFA§7] §cDer angegebene Spieler wurde nicht gefunden");
        german.addString("spawnlocation-set", "§7[§cFFA§7] §aDu hast den Spawnpunkt gesetzt");
        german.addString("scoreboard-kills", "§fKills");
        german.addString("scoreboard-deaths", "§fTode");
        german.addString("scoreboard-killstreak", "§fKillstreak");
        german.addString("scoreboard-killstreak-value", "§9%killstreak%");
        german.addString("scoreboard-deaths-value", "§c%deaths%");
        german.addString("scoreboard-kills-value", "§a%kills%");
        german.addString("scoreboard-map", "§fMap");
        german.addString("scoreboard-map-value", "§d%motd%");
        german.addString("kit-saved", "§7[§cFFA§7] §aInventory gespeichert");
        german.saveAndCreate();
        event.getCloudServer().registerLanguages();
    }
}

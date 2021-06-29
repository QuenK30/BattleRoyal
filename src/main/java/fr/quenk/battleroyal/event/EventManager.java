package fr.quenk.battleroyal.event;

import fr.quenk.battleroyal.BRMain;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

/*
 *QuenK_ 27/06/2021 | 11:15 |BattleRoyal
 */
public class EventManager {
    private BRMain plugin;

    public EventManager(BRMain plugin) {
        this.plugin = plugin;
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BRPListerner(plugin), plugin);
        pm.registerEvents(new BRPDamage(plugin), plugin);
    }
}

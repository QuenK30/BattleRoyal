package fr.quenk.battleroyal;

import fr.quenk.battleroyal.event.EventManager;
import fr.quenk.battleroyal.utils.BRState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class BRMain extends JavaPlugin {

    private BRState state;
    private List<Player> brplayer = new ArrayList<>();
    @Override
    public void onLoad() {
        System.out.println("Load");
        super.onLoad();
    }

    @Override
    public void onEnable() {
        System.out.println("Enable");
        new EventManager(this).registerEvents();
        setState(BRState.WAITING);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        System.out.println("disable");
        super.onDisable();
    }

    public void setState(BRState state) {
        this.state = state;
    }

    public boolean isState(BRState state) {
        return this.state == state;
    }

    public List<Player> getBrplayer() {
        return brplayer;
    }
}

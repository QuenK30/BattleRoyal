package fr.quenk.battleroyal;

import fr.mrmicky.fastboard.FastBoard;
import fr.quenk.battleroyal.event.EventManager;
import fr.quenk.battleroyal.utils.BRState;
import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class BRMain extends JavaPlugin {
    public final Map<UUID, FastBoard> boards = new HashMap<>();
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

        getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, 20);

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

    private void updateBoard(FastBoard board) {
        board.updateLines(
                "",
                ChatColor.GRAY+"Players: " + getServer().getOnlinePlayers().size(),
                "",
                ChatColor.GRAY+"Kills: " + board.getPlayer().getStatistic(Statistic.PLAYER_KILLS),
                "",
                ChatColor.GRAY+"State: " + state.name(),
                "",
                ChatUtils.PUB.getMessage()
        );
    }
}

package fr.quenk.battleroyal.event;

import fr.mrmicky.fastboard.FastBoard;
import fr.quenk.battleroyal.BRMain;
import fr.quenk.battleroyal.tasks.BRAutoStart;
import fr.quenk.battleroyal.utils.BRState;
import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.bukkit.Bukkit.getServer;

/*
 *QuenK_ 27/06/2021 | 11:16 |BattleRoyal
 */
public class BRPListerner implements Listener {

    private BRMain main;

    public BRPListerner(BRMain main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();

        event.setJoinMessage(ChatUtils.PREFIX.getMessage()+" "+ChatColor.GRAY+p.getName()+" "+ChatUtils.PLUS.getMessage());

        FastBoard board = new FastBoard(p);
        board.updateTitle(ChatColor.RED + ChatUtils.PREFIX.getMessage());
        main.boards.put(p.getUniqueId(), board);

        if(!main.isState(BRState.WAITING) && !main.getBrplayer().contains(p)){

            p.kickPlayer("The game as already started");
            event.setJoinMessage(null);
            return;

        }

        if(!main.getBrplayer().contains(p)){

            main.getBrplayer().add(p);

            p.getInventory().clear();
            p.setGameMode(GameMode.ADVENTURE);
            p.setFoodLevel(20);
            p.setHealth(20);
            p.setAllowFlight(true);
            p.setLevel(0);
            p.setExp(0);
            p.setStatistic(Statistic.PLAYER_KILLS,0);

        }
        if(main.isState(BRState.WAITING)&& main.getBrplayer().size() == 2){

            BRAutoStart brAutoStart = new BRAutoStart(main);
            brAutoStart.runTaskTimer(main,0,20);
            main.setState(BRState.STARTING);

        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        event.setQuitMessage(ChatUtils.PREFIX.getMessage()+" "+ChatColor.GRAY+p.getName()+" "+ChatUtils.MOIN.getMessage());

        if(!main.isState(BRState.WAITING) && !main.getBrplayer().contains(p)){
            event.setQuitMessage(null);
        }
        if(Bukkit.getOnlinePlayers().size() == 1 || Bukkit.getOnlinePlayers().size() == 0){
            Bukkit.shutdown();
        }

        FastBoard board = main.boards.remove(p.getUniqueId());

        if (board != null) {
            board.delete();
        }

    }
}

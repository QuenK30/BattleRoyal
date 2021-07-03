package fr.quenk.battleroyal.tasks;

import fr.quenk.battleroyal.BRMain;
import fr.quenk.battleroyal.utils.BRState;
import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/*
 *QuenK_ 27/06/2021 | 19:46 |BattleRoyal
 */
public class BRGameCycle extends BukkitRunnable {

    private BRMain main;

    public BRGameCycle(BRMain main) {
        this.main = main;
    }

    private int timer = 1800;
    @Override
    public void run() {
        if(timer == 900){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ ChatColor.GRAY+" 15 minutes until PVP.");
        }
        if(timer == 1800){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" 30 minutes until PVP.");
        }
        if(timer == 300){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" 5 minutes until PVP.");
        }
        if(timer == 4||timer == 3|| timer == 2 || timer ==1 || timer == 5 || timer == 15 || timer == 10 || timer == 30 || timer == 60){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+timer+" seconds until pvp.");
        }
        if(timer == 0){

            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ ChatColor.GRAY+" Let the game begin! :)");

            for(Player player : main.getBrplayer()){
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT,20f,20f);
            }
            main.setState(BRState.PVP);

            cancel();
        }
        timer--;
    }
}

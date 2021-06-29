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

    private int timer = 3600;
    @Override
    public void run() {
        if(timer == 900){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ ChatColor.GRAY+" 15 minutes until deathmatch.");
        }
        if(timer == 1800){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" 30 minutes until deathmatch.");
        }
        if(timer == 2700){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" 45 minutes until deathmatch.");
        }
        if(timer == 4||timer == 3|| timer == 2 || timer ==1 || timer == 5 || timer == 15 || timer == 10 || timer == 30 || timer == 60){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+timer+" seconds until deathmatch.");
        }
        if(timer == 0){

            World world = Bukkit.getWorld("world");
            Location location = new Location(world, 0, 0, 0);
            location.setX( location.getX() + Math.random() * 10 * 2 - 10);
            location.setZ( location.getZ() + Math.random() * 10 * 2 - 10);

            location.setY( world.getHighestBlockAt(location.getBlockX(), location.getBlockZ() ).getY()+1);

            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ ChatColor.GRAY+" Let the final fight begin! :)");

            for(Player player : main.getBrplayer()){
                player.teleport(location);
                player.playSound(player.getLocation(), Sound.EVENT_RAID_HORN,20f,20f);
            }
            main.setState(BRState.DEATHMATCH);

            cancel();
        }
        timer--;
    }
}

package fr.quenk.battleroyal.tasks;

import fr.quenk.battleroyal.BRMain;
import fr.quenk.battleroyal.utils.BRState;
import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/*
 *QuenK_ 27/06/2021 | 11:44 |BattleRoyal
 */

public class BRAutoStart extends BukkitRunnable {

    private BRMain main;

    public BRAutoStart(BRMain main) {
        this.main = main;
    }

    private  int timer = 30;
    @Override
    public void run() {
        for(Player pls : Bukkit.getOnlinePlayers()){
            pls.setLevel(timer);
        }
        if(timer == 10 || timer == 20 || timer == 5 ||timer == 4||timer == 3||timer == 2 || timer == 1){
            for(Player pls : main.getBrplayer()){
                pls.playSound(pls.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,20,20);
            }
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" The battle will start in "+ChatColor.RED+ timer +ChatColor.GRAY+" seconds!");
        }
        if(timer == 0){
            for(Player pls : main.getBrplayer()){
                pls.playSound(pls.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT,20,20);
            }
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ ChatColor.GRAY+" Let the battle rage!");
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" DeathMatch in"+ChatColor.RED+" 60 "+ChatColor.GRAY+"minutes !");
            main.setState(BRState.GAME);

            //0 0
            World world = Bukkit.getWorld("world");
            Location location = new Location(world, 0, 0, 0);
            location.setX( location.getX() + Math.random() * 10 * 2 - 10);
            location.setZ( location.getZ() + Math.random() * 10 * 2 - 10);

            location.setY( world.getHighestBlockAt(location.getBlockX(), location.getBlockZ() ).getY()+1);

            for(Player pls : Bukkit.getOnlinePlayers()){
                pls.teleport(location);

                pls.getInventory().clear();
                pls.setGameMode(GameMode.SURVIVAL);
                pls.setFoodLevel(20);
                pls.setHealth(20);
                pls.setAllowFlight(false);
                pls.setLevel(0);
                pls.setExp(0);

            }



            BRGameCycle gameCycle = new BRGameCycle(main);
            gameCycle.runTaskTimer(main,0,20);

            cancel();
        }

        timer--;
    }
}

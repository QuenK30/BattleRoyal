package fr.quenk.battleroyal.tasks;

import fr.quenk.battleroyal.BRMain;
import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;

/*
 *QuenK_ 03/07/2021 | 15:51 |BattleRoyal
 */
public class BRBorderTwo extends BukkitRunnable {
    private BRMain main;

    public BRBorderTwo(BRMain main) {
        this.main = main;
    }

    private int timer = 3600;
    @Override
    public void run() {
        World world = Bukkit.getWorld("world");
        WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(0,0);
        if(timer == 1800){
            worldBorder.setDamageAmount(2);
            worldBorder.setSize(1000);
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" the border is 1000 x 1000");
        }
        if(timer == 900){
            worldBorder.setSize(500);
            worldBorder.setDamageAmount(4);
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" the border is 500 x 500");
        }
        if(timer == 300){
            worldBorder.setSize(100);
            worldBorder.setDamageAmount(6);
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ ChatColor.GRAY+" the border is 100 x 100");
        }
        if(timer == 0){
            cancel();
        }
        timer--;
    }
}

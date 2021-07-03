package fr.quenk.battleroyal.tasks;

import fr.quenk.battleroyal.BRMain;
import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.scheduler.BukkitRunnable;

/*
 *QuenK_ 03/07/2021 | 15:42 |BattleRoyal
 */
public class BRBorder extends BukkitRunnable {

    private BRMain main;

    public BRBorder(BRMain main) {
        this.main = main;
    }

    private int timer = 3600;

    @Override
    public void run() {
        if(timer == 900){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ ChatColor.GRAY+" 15 minutes before the border shrinks.");
        }
        if(timer == 1800){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" 30 minutes before the border shrinks.");
        }
        if(timer == 300){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" 5 minutes before the border shrinks.");
        }
        if(timer == 4||timer == 3|| timer == 2 || timer ==1 || timer == 5 || timer == 15 || timer == 10 || timer == 30 || timer == 60){
            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+" "+ ChatColor.GRAY+timer+" seconds before the border shrinks.");
        }
        if(timer == 0){
            World world = Bukkit.getWorld("world");
            WorldBorder worldBorder = world.getWorldBorder();
            worldBorder.setCenter(0,0);
            worldBorder.setSize(4000);

            Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+" the border is 2000 x 2000");

            BRBorderTwo bordertwo = new BRBorderTwo(main);
            bordertwo.runTaskTimer(main,0,20);
            cancel();
        }
        timer--;

    }
}

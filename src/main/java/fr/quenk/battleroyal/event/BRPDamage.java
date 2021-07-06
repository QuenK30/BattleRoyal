package fr.quenk.battleroyal.event;

import fr.quenk.battleroyal.BRMain;
import fr.quenk.battleroyal.utils.BRState;
import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import static org.bukkit.Bukkit.getServer;

/*
 *QuenK_ 28/06/2021 | 17:20 |BattleRoyal
 */
public class BRPDamage implements Listener {
    private BRMain main;

    public BRPDamage(BRMain main) {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (main.isState(BRState.WAITING)|| main.isState(BRState.STARTING)) {
                System.out.println("waiting");
                event.setCancelled(true);
                player.sendMessage(ChatUtils.PREFIX.getMessage() + ChatColor.GRAY + " The game has not started!");

            }
            if(main.isState(BRState.GAME)){
                System.out.println("NO PVP");
                event.setCancelled(true);
                player.sendMessage(ChatUtils.PREFIX.getMessage() + ChatColor.GRAY + " The PvP has not started!");
            }
            if (main.isState(BRState.PVP) ) {
                if (!day()){
                    event.setCancelled(false);
                    return;
                }else{
                    player.sendMessage(ChatUtils.PREFIX.getMessage() + ChatColor.GRAY + " You can PvP only at night");
                    event.setCancelled(true);
                }
                System.out.println("game or deathmatch");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        Player player = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();


        if (event.getDeathMessage().contains("hit the ground too hard")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " didn't see where he was going and fell off a cliff !");
            return;
        }
        if (event.getDeathMessage().contains("was shot by Skeleton")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " looks like a Hedgehog !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by Spider")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " wanted to become a Spider-Man ! (It didn't work well)");
            return;
        }
        if (event.getDeathMessage().contains("was blown up by Creeper")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " made friends with a Creeper !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by Husk")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " it's chained by a Husk !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by Zombie")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " wanted to hug a Zombie !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by Slime")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " is now slimy !");
            return;
        }
        if (event.getDeathMessage().contains("was slain by")) {
            event.setDeathMessage(ChatUtils.PREFIX.getMessage() + ChatColor.RED + player.getName() + ChatColor.GRAY + " was eliminated by " + ChatColor.RED + killer.getName());
            main.getBrplayer().remove(player);
            player.kickPlayer("You are dead");
            if(Bukkit.getOnlinePlayers().size() == 1){
                killer.kickPlayer("CONGRATULATIONS YOU WIN! (with "+player.getStatistic(Statistic.PLAYER_KILLS)+" kills)");
            }
            return;
        }
        event.setDeathMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+player.getName()+" died miserably !");
    }
   public boolean day() {
        Server server = getServer();
        long time = server.getWorld("world").getTime();

        return time < 12300 || time > 23850;
    }
}

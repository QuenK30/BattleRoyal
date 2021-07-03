package fr.quenk.battleroyal.event;

import fr.quenk.battleroyal.BRMain;
import fr.quenk.battleroyal.utils.BRState;
import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

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
            if(main.isState(BRState.PVP)){
                System.out.println("NO PVP");
                event.setCancelled(true);
                player.sendMessage(ChatUtils.PREFIX.getMessage() + ChatColor.GRAY + " The PVP has not started!");
            }
            if (main.isState(BRState.GAME) || main.isState(BRState.DEATHMATCH) ) {
                if (day()){
                    event.setCancelled(false);
                    return;
                }
                System.out.println("game or deathmatch");
                System.out.println("day");
                event.setCancelled(true);
            }
        }else{
            System.out.println("mob");
        }
    }

   public boolean day() {
        Server server = getServer();
        long time = server.getWorld("world").getTime();

        return time < 12300 || time > 23850;
    }
}

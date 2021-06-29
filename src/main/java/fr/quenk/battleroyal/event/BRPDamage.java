package fr.quenk.battleroyal.event;

import fr.quenk.battleroyal.BRMain;
import fr.quenk.battleroyal.utils.BRState;
import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/*
 *QuenK_ 28/06/2021 | 17:20 |BattleRoyal
 */
public class BRPDamage implements Listener {
    private BRMain main;

    public BRPDamage(BRMain main) {
        this.main = main;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
       /* if(main.isState(BRState.WAITING)) {
                event.setCancelled(true);
                player.sendMessage(ChatUtils.PREFIX.getMessage() + ChatColor.GRAY + " The game has not started!");
                return;
        }
        if(main.isState(BRState.GAME)|| main.isState(BRState.DEATHMATCH)){
            event.setCancelled(false);
        }*/
    }

}

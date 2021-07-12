package fr.quenk.battleroyal.cmd;

import fr.quenk.battleroyal.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 *QuenK_ 06/07/2021 | 13:31 |BattleRoyal
 */
public class BRCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(player.isOp()){
            if(args.length == 0){
                player.sendMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+"Arguments: start, stop, pause");
            }
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("start")){
                    Bukkit.broadcastMessage(ChatUtils.PREFIX.getMessage()+ChatColor.GRAY+player.getName()+" has started the game");
                }
            }
        }else{
            player.sendMessage(ChatUtils.PREFIX.getMessage()+ ChatColor.GRAY+"You can't do this");
        }
        return false;
    }
}

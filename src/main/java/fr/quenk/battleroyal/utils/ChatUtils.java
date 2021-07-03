package fr.quenk.battleroyal.utils;

import org.bukkit.ChatColor;

/*
 *QuenK_ 27/06/2021 | 11:05 |BattleRoyal
 */
public enum ChatUtils {

    PREFIX(ChatColor.WHITE+"﴾"+ ChatColor.GRAY+" Battle"+ChatColor.RED+"Royal "+ChatColor.WHITE+"﴿ " ),
    PUB(ChatColor.WHITE+"﴾"+ ChatColor.GRAY+" By"+ChatColor.RED+" QuenK "+ChatColor.WHITE+"﴿ " ),
    PLUS(ChatColor.GRAY+"["+ChatColor.GREEN+"+"+ChatColor.GRAY+"]"),
    MOIN(ChatColor.GRAY+"["+ChatColor.GREEN+"-"+ChatColor.GRAY+"]"),
    RECONNECTED(ChatColor.GRAY+"["+ChatColor.GREEN+"RECONNECTED"+ChatColor.GRAY+"]");

    private final String message;

    ChatUtils(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
package org.betterservernetwork.ezcolor;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class ColorCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(
            CommandSender commandSender,
            Command command, String s,
            String[] arguments) {
        if (!(commandSender instanceof Player)) {
            return true;
        }

        Player player = (Player) commandSender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (arguments.length != 1) {
            player.sendMessage(ChatColor.RED + "Correct usage: " + ChatColor.RESET + "/color <hex>");
            return true;
        } else if (item.getType() == Material.AIR || (item.getType() != Material.LEATHER_HELMET &&
                item.getType() != Material.LEATHER_CHESTPLATE &&
                item.getType() != Material.LEATHER_LEGGINGS &&
                item.getType() != Material.LEATHER_BOOTS)) {
            player.sendMessage(ChatColor.RED + "You need to be holding leather armor.");
            return true;
        }

        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();

        java.awt.Color color;

        try {
            color = java.awt.Color.decode(arguments[0]);
        } catch (NumberFormatException ignored) {
            player.sendMessage(ChatColor.RED + "Incorrect number format.");
            return true;
        }

        meta.setColor(Color.fromRGB(color.getRed(), color.getGreen(), color.getBlue()));

        item.setItemMeta(meta);

        player.sendMessage("Color set.");

        return true;
    }

    @Override
    public List<String> onTabComplete(
            CommandSender commandSender,
            Command command,
            String s,
            String[] arguments) {
        return new ArrayList<>();
    }
}

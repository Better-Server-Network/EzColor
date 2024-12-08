package org.betterservernetwork.ezcolor;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class EzColor extends JavaPlugin {
    @Override
    public void onEnable() {
        ColorCommand command = new ColorCommand();

        Objects.requireNonNull(getCommand("ezcolor")).setExecutor(command);
        Objects.requireNonNull(getCommand("ezcolor")).setTabCompleter(command);

        System.out.println("Enabled.");
    }
}

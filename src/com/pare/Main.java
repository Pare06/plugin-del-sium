package com.pare;

import com.pare.commands.ExampleCommand;
import com.pare.events.EventHandlers;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

// IDEA pensa che la classe non venga mai chiamata, in realtà è disabile
// viene chiamata in plugin.yml riga 3
// @SuppressWarnings serve a ignorare qualsiasi errore inserito nelle ( )
@SuppressWarnings("unused")
public class Main extends JavaPlugin {
    // IDEA considera getCommand() un errore perchè il comando getCommand("sium") potrebbe ritornare null
    // se il comando sium non è dichiarato in plugin.yml
    // però 100% non lo è perchè è dichiarato alla riga 6
    @SuppressWarnings("ConstantConditions")
    @Override
    // onEnable è il metodo che viene chiamato per primo nel plugin
    public void onEnable() {
        // definizione: PluginManager.registerEvents(Listener listener, Plugin plugin)
        // listener è una classe qualsiasi che implementa l'interfaccia Listener
        // plugin è il plugin a cui si vogliono attivare gli eventi
        // il metodo attiva tutti gli eventi in EventHandlers (tutti i metodi con attributo @EventHandler)
        // nel plugin 'this', ovvero la classe Main
        getServer().getPluginManager().registerEvents(new EventHandlers(), this);
        // assegna la classe ExampleCommand al comando sium
        // la classe deve implementare CommandExecutor
        // e deve avere esattamente 1 metodo '@Override
        //                                    public boolean onCommand(..)'
        getCommand("sium").setExecutor(new ExampleCommand());
        // scrive nella console
        Bukkit.getLogger().info("Plugin attivato");
    }

    @Override
    // onDisable viene chiamato al reload/stop del server
    public void onDisable() {
        Bukkit.getLogger().info("Plugin disattivato");
    }
}
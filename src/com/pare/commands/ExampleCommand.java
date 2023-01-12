package com.pare.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

public class ExampleCommand implements CommandExecutor {
    @Override
    // onCommand viene chiamato quando viene eseguito il comando specificato sul file Main (riga 22)
    // commandSender -> il player/console che ha eseguito il comando
    // command -> info sul comando eseguito
    // s -> alias del comando eseguito (ad esempio, /tpa ha come alias /tpask)
    // strings -> i parametri del comando (in '/tp ndiaye diallo' i parametri sono ndiaye e diallo)
    // tutti i parametri di onCommand hanno @NotNull perchè non possono essere null, vengono sempre dichiarati
    // da spigot. con @NotNull IDEA non dà avvertimenti che c'entrano con le funzioni che non accettano
    // parametri null
    //
    // se onCommand ritorna true allora il comando si può considerare eseguito senza errori nei parametri
    // (ad esempio, fare '/tp ndiaye' quando ndiaye non è nel server)
    // se invece ci sono errori nei parametri il metodo dovrà ritornare false
    // e al giocatore che ha eseguito il comando verrà inviato un messaggio contenente la definizione corretta
    // del comando (plugin.yml riga 8)
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // 'class instanceof class2' ritornerà true se class implementa/estende l'interfaccia/classe class2
        // in questo caso, ritornerà true se il comando è stato eseguito da console
        if (commandSender instanceof ConsoleCommandSender) {
            Bukkit.getLogger().info("Non puoi eseguire questo comando dalla console!");
            return true;
        }

        String name = commandSender.getName();
        Bukkit.broadcastMessage(String.format("%s ha siummato fortissimo!", name));

        return true;
    }
}

package com.pare.commands;

import com.pare.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class DelayedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // quando c'è da dichiarare una variable che rappresenta dei tick
        // è più worth usare (20 * N secondi) invece di N tick perchè è più easy da leggere
        // ad esempio, se bisogna aspettare 1 ora per fare della roba
        // è più facile da leggere (20 * 3600) invece di (72000)
        // o ancora meglio, (20 * 60 * 60 * 1), ma forse è un pò troppo
        int ticks = 20 * 5; // 5 secondi

        // per eseguire del codice dopo N tick si può usare la classe BukkitRunnable
        // è una classe che richiede un solo argomento per essere inizializzata, ovvero una funzione "anonima",
        // ovvero che non viene dichiarata con, per esempio, 'public void run() { }'
        // ma solo con { }
        // la funzione deve essere scritta dopo il () perchè sì
        //
        // nel java esiste la funzione Thread.sleep() che serve per aspettare n millisecondi, ma NON va mai usata
        // perchè non ferma la funzione dov'è collocato sleep(), ma ferma tutto il server
        //
        // se vi dovreste trovare in difficoltà con i BukkitRunnable e provate a cercare online come usarli
        // potrebbe uscire una cosa del genere:
        //
        // Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
        //      (codice)
        // }, (ticks) );
        //
        // è un modo stra-vecchio per eseguire codice dopo N ticks, è simile al BukkitRunnable
        // ma è più difficile da fermare ed è instabile perchè ogni aggiornamento di spigot viene
        // rotto sempre di più
        // e poi bruh perchè dovresti usare una funzione lunga come il cazzo del kebe
        // quando BukkitRunnable è cortissimo e non è difficile da leggere
        new BukkitRunnable() {
            // il codice che verrà eseguito dal BukkitRunnable deve essere inserito
            // dentro la funzione 'public void run()'
            // cambiare il nome della funzione o il suo tipo non farà compilare il plugin
            // deve avere l'attributo @Override perchè run() è già dichiarato in BukkitRunnable,
            // e bisogna sovrascriverlo
            @Override
            public void run() {
                commandSender.sendMessage(String.format("Sono passati %s secondi!", elapsedSeconds()));
            }

            // dentro un BukkitRunnable si possono dichiarare altre funzioni che possono essere chiamate da run()
            // l'importante è che non abbiamo @Override e che non si chiamino run(), anche se hanno tipo diverso
            private int elapsedSeconds() {
                return ticks / 20;
            }
        // il BukkitRunnable verrà fatto partire da Main.Plugin, e partirà dopo N tick (1/20 di un secondo)
        }.runTaskLater(Main.Plugin, ticks);

        return true;
    }
}

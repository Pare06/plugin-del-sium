package com.pare.commands;

import com.pare.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class RepeatingCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // prima leggi DelayedCommand, non ho intenzione di scrivere la stessa roba 2 volte

        // "non si può fare int timesRan = 0?"
        // no, perchè java fa cagare e non si può cambiare il valore di una variabile base (int, bool, float, char ..)
        // da una funzione anonima
        // per girare intorno a questo problema bisogna dichiarare un array di grandezza 1 contenente
        // la variabile che vogliamo cambiare
        // l'array deve essere final perchè non può deve poter essere inizializzata per una seconda volta
        // però cambiando timesRan[0] l'array non viene inizializzata, ma solo timesRan[0]
        // quindi va bene
        final int[] timesRan = {0};
        int ticksDelay = 20 * 1; // 1 secondo
        new BukkitRunnable() {
            @Override
            public void run() {
                timesRan[0]++;

                // String.valueOf(Object obj) trasforma o in una stringa, se possibile
                commandSender.sendMessage(String.format("Sono passati %s secondi!", timesRan[0]));

                // se run() è stato eseguito 10 volte
                if (timesRan[0] == 10) {
                    // allora con cancel() il BukkitRunnable smette di ripetersi
                    // in ogni BukkitRunnable attivato con runTaskTimer() è obbligatorio mettere almeno un cancel()
                    // per evitare che continui all'infinito, con la certezza che prima o poi causerà
                    // eccezioni quando è passato troppo tempo dalla prima esecuzione e le variabili che chiama
                    // diventano o nulle, o che possano contenere valori non previsti da run()
                    cancel();
                }
            }
        // con runTaskTimer(JavaPlugin plugin, int delay, int period)
        // il BukkitRunnable viene eseguito la prima volta dopo 'delay' ticks
        // e poi di nuovo ogni 'period' ticks, per sempre
        // o finchè il BukkitRunnable non viene interrotto con BukkitRunnable.cancel();
        }.runTaskTimer(Main.Plugin, ticksDelay, ticksDelay);

        return true;
    }
}

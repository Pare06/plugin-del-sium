package com.pare.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class EventHandlers implements Listener {
    // tutti gli eventi devono avere l'attributo @EventHandler
    // EventHandler può avere il parametro (opzionale) priority, un enum che rappresenta la priorità
    // nell'esecuzione dell'evento
    // (esempio: @EventHandler(priority = EventPriority.HIGH)
    //
    // gli eventi vengono chiamati nell'ordine
    // LOWEST -> LOW -> NORMAL -> HIGH -> HIGHEST -> MONITOR
    // dove i primi eventi a essere chiamati sono quelli di priorità LOWEST così quelli con priorità
    // più alta hanno l'ultima parola sull'esito dell'evento
    // la priorità MONITOR serve solo ad analizzare il risultato di un evento per, ad esempio, salvarlo in un database
    // non può fare cambiamenti all'evento (ad esempio teletrasportando un giocatore)
    @EventHandler(priority = EventPriority.HIGH)
    // tutti gli eventi creati dal server non possono essere null
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("è arrivata mammina la piscia r ta mamma scrivi scrivi");
        player.sendMessage(ChatColor.RED + "si sol n'abbunat");
    }
}

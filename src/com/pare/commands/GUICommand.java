package com.pare.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // console?
        if (commandSender instanceof ConsoleCommandSender) {
            return true;
        }

        // CommandSender -> Player
        Player player = (Player) commandSender;

        // crea un inventario (utilizzabile come interfaccia grafica)
        // Bukkit.createInventory(InventoryHolder owner, int size, String title)
        // owner è il player "proprietario" dell'inventario
        // se l'inventario è qualcosa che dovrebbe essere accessibile solo a un player (come un enderchest)
        // allora owner deve essere un player nel server
        // se invece l'inventario è un'interfaccia a cui possono accedere tutti (ad esempio un lobby selector)
        // allora owner deve essere null
        // size è il numero di slot dell'inventario (deve essere multiplo di 9 e non superiore a 54)
        // title è il nome dell'inventario, scritto in alto a sinistra dell'interfaccia
        // se l'inventario ha 27 slot, gli slot sono enumerati in questo ordine:
        // 0  1  2  3  4  5  6  7  8
        // 9  10 11 12 13 14 15 16 17
        // 18 19 20 21 22 23 24 25 26
        Inventory inv = Bukkit.createInventory(null, 27, "COPYPASTA SELEZIONATORE");

        ItemStack item = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta meta = Objects.requireNonNull(item.getItemMeta());

        // riempie l'inventario di glass pane
        for (int i = 0; i < 27; i++) {
            inv.setItem(i, new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
        }
        // mette un blocco di diamante con nome "avigliano" all'indice 10
        meta.setDisplayName("avigliano");
        item.setItemMeta(meta);
        inv.setItem(10, item);
        // "herobrine", indice 12
        meta.setDisplayName("puglia");
        item.setItemMeta(meta);
        inv.setItem(12, item);
        // "simone", indice 14
        meta.setDisplayName("simone");
        item.setItemMeta(meta);
        inv.setItem(14, item);
        // "bona dea", indice 16
        meta.setDisplayName("bona dea");
        item.setItemMeta(meta);
        inv.setItem(16, item);

        // apre l'interfaccia al player
        player.openInventory(inv);
        return true;
    }
}

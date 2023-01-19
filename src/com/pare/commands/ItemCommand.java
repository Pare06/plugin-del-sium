package com.pare.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class ItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // console?
        if (commandSender instanceof ConsoleCommandSender) {
            return true;
        }

        // non si possono dare item a un CommandSender, quindi bisogna dichiarare un'oggetto Player
        // e convertire commandSender in un Player
        Player player = (Player) commandSender;

        ArrayList<String> lore = new ArrayList<>();
        lore.add("questo stick è sus");
        lore.add("ha scannato tanti cristiani");

        // per rappresentare un item bisogna usare la classe ItemStack
        // nel costruttore bisogna specificare un parametro di tipo Material, un enum che comprende
        // ogni oggetto di minecraft
        ItemStack item = new ItemStack(Material.STICK);
        // la classe ItemMeta contiene tutte le informazioni di un item
        // item.getItemMeta() non può ritornare null perchè l'oggetto è appena stato creato,
        // quindi con Objects.requireNonNull() IDEA non crea avvertimenti dopo la riga 40
        ItemMeta meta = Objects.requireNonNull(item.getItemMeta());

        // aggiunge sharpness V all'oggetto (il boolean alla fine serve per ignorare le restrizioni
        // degli enchant vanilla (se fosse false, addEnchant() fallirebbe perchè uno stick non può
        // avere sharpness 5
        meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        // cambia il nome dell'item
        meta.setDisplayName("Lo scannacristiani");
        // imposta la descrizione dell'oggetto, che deve essere una ArrayList<String>
        // ogni elemento rappresenta una riga della descrizione
        meta.setLore(lore);
        // quando si è finito di usare metodi che cambiano l'ItemMeta bisogna usare la funzione
        // ItemStack.setItemMeta() perchè per qualche motivo non si salvano da soli i cambiamenti
        // all'item meta
        item.setItemMeta(meta);

        player.getInventory().addItem(item);

        return true;
    }
}

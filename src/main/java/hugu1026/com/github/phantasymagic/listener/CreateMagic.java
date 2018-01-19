package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.CreateMagicEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateMagic implements Listener {

    @EventHandler
    public void CreateMagic(CreateMagicEvent event) {
        HashMap<Integer, ItemStack> magicIcons = event.getMagicIcons();
        Player player = event.getPlayer();

        ItemStack MAGIC_WAND = new ItemStack(Material.STICK, 1);
        ItemMeta wandMeta = MAGIC_WAND.getItemMeta();
        List<String> lore = new ArrayList<>();

        for (int key : magicIcons.keySet()) {
            ItemStack magicIcon = magicIcons.get(key);
            String slotNumber = String.valueOf(key);
            String magiclore = ChatColor.GREEN + slotNumber + ": " + magicIcon.getItemMeta().getDisplayName();
            lore.add(magiclore);
        }

        wandMeta.setDisplayName(ChatColor.BLUE + "魔法の杖");
        wandMeta.setLore(lore);

        MAGIC_WAND.setItemMeta(wandMeta);

        player.getInventory().addItem(MAGIC_WAND);
    }
}
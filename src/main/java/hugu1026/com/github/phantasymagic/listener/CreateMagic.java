package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.CreateMagicEvent;
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
        int sumMana = 0;

        for (int key : magicIcons.keySet()) {
            ItemStack magicIcon = magicIcons.get(key);
            String slotNumber = String.valueOf(key);
            String[] NameandMana = magicIcon.getItemMeta().getDisplayName().split(" /");
            String magicName = ChatColor.GREEN + slotNumber + ": " + NameandMana[0];
            int magicMana = Integer.parseInt(NameandMana[1].replace(" マナ", ""));
            lore.add(magicName);
            sumMana = sumMana + magicMana;
        }

        wandMeta.setDisplayName(ChatColor.BLUE + "魔法の杖 " + "必要マナ: " + ChatColor.LIGHT_PURPLE + sumMana);
        wandMeta.setLore(lore);

        MAGIC_WAND.setItemMeta(wandMeta);

        player.getWorld().dropItem(player.getLocation().add(0, 0.5, 0), MAGIC_WAND).setPickupDelay(0);
    }
}
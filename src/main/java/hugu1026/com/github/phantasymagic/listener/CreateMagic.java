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
        int sumNeedSpirit = 0;
        ItemStack spirit = new ItemStack(Material.PRISMARINE_SHARD, 1);
        ItemMeta meta = spirit.getItemMeta();
        meta.setDisplayName("スピリット");
        meta.setLore(null);
        spirit.setItemMeta(meta);

        for (int key : magicIcons.keySet()) {
            ItemStack magicIcon = magicIcons.get(key);
            String slotNumber = String.valueOf(key);
            String[] magicInfo = magicIcon.getItemMeta().getDisplayName().split(" /");
            String magicName = ChatColor.GREEN + slotNumber + ": " + magicInfo[0];
            int magicMana = Integer.parseInt(magicInfo[1].replace(" マナ", ""));
            int needSpirit = Integer.parseInt(magicInfo[2].replace(" スピリット", ""));
            lore.add(magicName);
            sumMana = sumMana + magicMana;
            sumNeedSpirit = sumNeedSpirit + needSpirit;
        }

        int spiritAmount = 0;
        ItemStack[] items = player.getInventory().getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getType() == spirit.getType() && items[i].getDurability() == spirit.getDurability()) {
                spiritAmount += items[i].getAmount();
            }
        }

        if (spiritAmount >= sumNeedSpirit) {
            wandMeta.setDisplayName(ChatColor.BLUE + "魔法の杖 " + "必要マナ: " + ChatColor.LIGHT_PURPLE + sumMana);
            wandMeta.setLore(lore);

            MAGIC_WAND.setItemMeta(wandMeta);

            player.getWorld().dropItem(player.getLocation().add(0, 0.5, 0), MAGIC_WAND).setPickupDelay(0);

            spirit.setAmount(sumNeedSpirit);
            player.getInventory().removeItem(spirit);
        } else {
            player.sendMessage(ChatColor.RED + "スピリットが足りない！ 必要スピリット: " + sumNeedSpirit);
        }
    }
}
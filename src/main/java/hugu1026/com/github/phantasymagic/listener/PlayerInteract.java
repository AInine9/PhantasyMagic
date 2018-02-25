package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import hugu1026.com.github.phantasymagic.event.MagicBookClickedEvent;
import hugu1026.com.github.phantasymagic.gui.MagicSquareGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerInteract implements Listener {

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            MagicSquareGui magicSquareGui = new MagicSquareGui();

            if (magicSquareGui.canOpen(block)) {
                event.setCancelled(true);
                magicSquareGui.openInventory(player);
            }
        }

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getInventory().getItemInMainHand() == null
                    || !player.getInventory().getItemInMainHand().hasItemMeta()
                    || !player.getInventory().getItemInMainHand().getItemMeta().hasLore()
                    || !player.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) return;

            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(ChatColor.BLUE + "魔法の杖")) {
                ActivateMagicEvent activateMagicEvent = new ActivateMagicEvent(player);
                Bukkit.getServer().getPluginManager().callEvent(activateMagicEvent);
            }

            if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("魔導書")) {
                MagicBookClickedEvent bookClickedEvent = new MagicBookClickedEvent(player);
                Bukkit.getServer().getPluginManager().callEvent(bookClickedEvent);
            }
        }


    }
}
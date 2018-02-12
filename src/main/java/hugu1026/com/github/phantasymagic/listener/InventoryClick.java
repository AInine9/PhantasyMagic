package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.CreateMagicEvent;
import hugu1026.com.github.phantasymagic.gui.MagicSelectGui;
import hugu1026.com.github.phantasymagic.gui.MagicSquareGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryClick implements Listener {

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();

        Inventory inventory = event.getInventory();
        if (inventory == null) return;

        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        if (inventory.getHolder() instanceof MagicSquareGui) {
            MagicSquareGui magicSquareGui = (MagicSquareGui) event.getInventory().getHolder();

            if (event.getRawSlot() > 44) return;

            if (clickedItem != null) {
                if (clickedItem.equals(magicSquareGui.getBARRIER_BLOCK())) {
                    event.setCancelled(true);
                    return;
                } else if (clickedItem.equals(magicSquareGui.getPANEL())) {
                    MagicSelectGui magicSelectGui = new MagicSelectGui(inventory, event.getRawSlot());

                    if (magicSelectGui.checkMagicAmount()) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "これ以上魔法を設置できない！");
                        return;
                    }

                    magicSelectGui.openInventory(player);
                } else if (clickedItem.equals(magicSquareGui.getPLAYER_HEAD())) {

                } else if (clickedItem.equals(magicSquareGui.getCREATE_MAGIC_ITEM())) {
                    CreateMagicEvent createMagicEvent = new CreateMagicEvent(event.getInventory(), player);

                    player.closeInventory();
                    Bukkit.getServer().getPluginManager().callEvent(createMagicEvent);
                }
                event.setCancelled(true);
            }
        }

        if (inventory.getHolder() instanceof MagicSelectGui) {
            MagicSelectGui magicSelectGui = (MagicSelectGui) event.getInventory().getHolder();

            if (clickedItem != null) {
                List<ItemStack> magicSets = magicSelectGui.getMagicSets();

                for (ItemStack magicIcon : magicSets) {
                    if (event.getCurrentItem().equals(magicIcon)) {
                        event.setCancelled(true);

                        int slotSource = magicSelectGui.getSlotSource();
                        Inventory guiSource = magicSelectGui.getGuiSource();

                        guiSource.setItem(slotSource, magicIcon);

                        player.openInventory(guiSource);
                    }
                }
            }
        }
    }
}
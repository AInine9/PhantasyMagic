package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.gui.MagicSquareGui;
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

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        MagicSquareGui magicSquareGui = new MagicSquareGui();

        if (magicSquareGui.canOpen(block)) {
            event.setCancelled(true);
            magicSquareGui.openInventory(player);
        }
    }
}
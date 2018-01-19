package hugu1026.com.github.phantasymagic.event;

import hugu1026.com.github.phantasymagic.gui.MagicSquareGui;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CreateMagicEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private HashMap<Integer, ItemStack> magicIcons = new HashMap<>();
    private Player player;

    public CreateMagicEvent(Inventory inventory, Player player) {
        this.player = player;

        for (int i = 0; i < 45; i++) {
            ItemStack itemStack = inventory.getItem(i);
            MagicSquareGui magicSquareGui = (MagicSquareGui) inventory.getHolder();

            if (itemStack == null) return;

            if (!(itemStack.equals(magicSquareGui.getCREATE_MAGIC_ITEM())
                    || itemStack.equals(magicSquareGui.getBARRIER_BLOCK())
                    || itemStack.equals(magicSquareGui.getPANEL())
                    || itemStack.equals(magicSquareGui.getPLAYER_HEAD()))) {
                magicIcons.put(i, itemStack);
            }
        }
    }

    public static  HandlerList getHandlerList() {
        return  handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public HashMap<Integer, ItemStack> getMagicIcons() {
        return this.magicIcons;
    }

    public Player getPlayer() {
        return this.player;
    }
}
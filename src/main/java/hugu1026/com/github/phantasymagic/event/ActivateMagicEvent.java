package hugu1026.com.github.phantasymagic.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class ActivateMagicEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private ItemStack MAGIC_WAND;
    private Player player;
    private HashMap<Integer, String> activateMagicList = new HashMap<>();

    public ActivateMagicEvent(Player player) {
        this.player = player;
        this.MAGIC_WAND = player.getInventory().getItemInMainHand();

        List<String> lores = this.MAGIC_WAND.getItemMeta().getLore();
        for (String lore : lores) {
            lore = ChatColor.stripColor(lore);
            if (!lore.contains(": ")) {
                continue;
            }
            String[] temp = lore.split(": ", 0);
            int slot = Integer.parseInt(temp[0]);
            String magic = temp[1];

            this.activateMagicList.put(slot, magic);
        }
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ItemStack getMAGIC_WAND() {
        return this.MAGIC_WAND;
    }

    public HashMap<Integer, String> getActivateMagicList() {
        return this.activateMagicList;
    }
}

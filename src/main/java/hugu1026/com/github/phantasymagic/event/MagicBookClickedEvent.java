package hugu1026.com.github.phantasymagic.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MagicBookClickedEvent extends Event{
    private static final HandlerList handlers = new HandlerList();
    private ItemStack magicBook;
    private String magicCalledName, magicName, magicDisplayName;
    private Player player;
    private static HashMap<String, String> magicList = new HashMap<>();
    static { //magic book name without "魔導書"
        magicList.put("火炎の", "fire");
        magicList.put("吹雪の", "freeze");
        magicList.put("爆裂の", "explosion");
        magicList.put("酸性水の", "acid rain");
        magicList.put("雷氷塊の", "icelc drop");
    }

    public MagicBookClickedEvent(Player player) {
        this.player = player;
        this.magicBook = player.getInventory().getItemInMainHand();
        this.magicBook.setAmount(1);
        this.magicCalledName = magicBook.getItemMeta().getDisplayName().replace("魔導書", "");
        this.magicName = magicList.get(magicCalledName);
        this.magicDisplayName = magicBook.getItemMeta().getLore().get(0);
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

    public ItemStack getMagicBook() {
        return this.magicBook;
    }

    public String getMagicCalledName() {
        return this.magicCalledName;
    }

    public String getMagicName() {
        return this.magicName;
    }

    public String getMagicDisplayName() {
        return ChatColor.stripColor(magicDisplayName);
    }
}

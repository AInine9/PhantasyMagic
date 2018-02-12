package hugu1026.com.github.phantasymagic.event;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerManaChangeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private int originalMana, manaDifference, maxMana;

    public PlayerManaChangeEvent(Player player, int originalMana, int manaDifference) {
        this.player = player;
        this.originalMana = originalMana;
        this.manaDifference = manaDifference;
        this.maxMana = PlayerDataUtil.getPlayerMAX_MANA(player);
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

    public int getOriginalMana() {
        return this.originalMana;
    }

    public int getManaDifference() {
        return this.manaDifference;
    }

    public int getMaxMana() {
        return this.maxMana;
    }
}

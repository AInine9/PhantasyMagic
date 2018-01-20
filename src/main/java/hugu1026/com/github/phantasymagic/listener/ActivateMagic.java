package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ActivateMagic implements Listener {

    @EventHandler
    public void ActivateMagic(ActivateMagicEvent event) {
        Bukkit.getServer().broadcastMessage("occured");
    }
}

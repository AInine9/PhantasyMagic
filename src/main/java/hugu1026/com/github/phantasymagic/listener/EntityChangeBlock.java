package hugu1026.com.github.phantasymagic.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class EntityChangeBlock implements Listener {

    @EventHandler
    public void onGroundBlock(EntityChangeBlockEvent event) {
        if (event.getEntity().hasMetadata("notPlace")) {
            event.getEntity().remove();
            event.setCancelled(true);
        }
    }
}

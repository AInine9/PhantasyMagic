package hugu1026.com.github.phantasymagic.listener;

import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;

public class DropExp implements Listener {

    @EventHandler
    public void EntityDeath(EntityDeathEvent event) {
        event.setDroppedExp(0);
    }

    @EventHandler
    public void EntitySpawn(EntitySpawnEvent event) {
        if (event.getEntity() instanceof ExperienceOrb) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event) {
        event.getEntity().setTotalExperience(event.getDroppedExp());
    }

    @EventHandler
    public void FurnaceExtract(FurnaceExtractEvent event) {
        event.setExpToDrop(0);
    }

    @EventHandler
    public void BlockBreak(BlockBreakEvent event) {
        event.setExpToDrop(0);
    }
}
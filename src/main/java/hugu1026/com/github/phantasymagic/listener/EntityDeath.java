package hugu1026.com.github.phantasymagic.listener;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class EntityDeath implements Listener {

    @EventHandler
    public void EntityDeath(EntityDeathEvent event) {
        Location location = event.getEntity().getLocation();

        if (event.getEntity().getKiller() != null
                && event.getEntity() instanceof Creature
                && event.getEntity().getCustomName() != null) {
            int random = new java.util.Random().nextInt(5) + 1;
            ItemStack spirit = new ItemStack(Material.PRISMARINE_SHARD, random);
            ItemMeta meta = spirit.getItemMeta();
            meta.setDisplayName("スピリット");
            meta.setLore(null);
            spirit.setItemMeta(meta);

            int playerLevel = PlayerDataUtil.getPlayerLEVEL(event.getEntity().getKiller());
            String[] mobName = event.getEntity().getCustomName().split(" ");
            int mobLevel = Integer.parseInt(mobName[0].replace("Lv.", ""));

            //適正レベルか
            if (mobLevel - 2 <= playerLevel
                    && playerLevel <= mobLevel + 3) {
                //20%
                int num = new java.util.Random().nextInt(6);
                if (num == 0) {
                    event.getEntity().getWorld().dropItem(location, spirit);
                }
            } else {
                //10%
                int num = new java.util.Random().nextInt(11);
                if (num == 0) {
                    event.getEntity().getWorld().dropItem(location, spirit);
                }
            }
        }
    }
}

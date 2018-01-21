package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import java.util.Collection;

public class Fire extends Magic {

    public Fire(String magicName, Event event, Integer slot) {
        super(magicName, event, slot);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation) {
        Collection<Entity> entities = Bukkit.getServer().getWorld("world").getNearbyEntities(magicLocation, 0.25, 5, 0.25);
        for (Entity entity : entities) {
            entity.setFireTicks(20 * 5);
        }
        Bukkit.getWorld("world").spawnParticle(Particle.LAVA, magicLocation.add(0, 1, 0), 20);
        Bukkit.getWorld("world").playSound(magicLocation, Sound.ENTITY_GHAST_SHOOT, 1, 0);
    }

    @Override
    public boolean checkMagicName(String magicName) {
        if (magicName.equals("ファイヤ")) {
            return true;
        } else {
            return false;
        }
    }
}

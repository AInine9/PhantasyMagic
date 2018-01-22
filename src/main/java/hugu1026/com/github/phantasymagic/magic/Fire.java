package hugu1026.com.github.phantasymagic.magic;

import hugu1026.com.github.phantasymagic.event.ActivateMagicEvent;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import java.util.Collection;

public class Fire extends Magic {

    public Fire(String magicName, Event event, Integer slot) {
        super(magicName, event, slot);
    }

    @Override
    public void ActivatedMagic(ActivateMagicEvent event, Location magicLocation) {
        Collection<Entity> entities = event.getPlayer().getWorld().getNearbyEntities(magicLocation, 0.25, 5, 0.25);
        for (Entity entity : entities) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                creature.setFireTicks(20 * 5);
            }
        }
        event.getPlayer().getWorld().spawnParticle(Particle.LAVA, magicLocation.add(0, 1, 0), 20);
        event.getPlayer().getWorld().playSound(magicLocation, Sound.ENTITY_GHAST_SHOOT, 1, 0);
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
